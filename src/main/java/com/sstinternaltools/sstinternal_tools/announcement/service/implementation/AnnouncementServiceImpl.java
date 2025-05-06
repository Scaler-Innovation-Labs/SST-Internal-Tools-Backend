package com.sstinternaltools.sstinternal_tools.announcement.service.implementation;

import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementRequest;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementResponse;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementUpdateRequest;
import com.sstinternaltools.sstinternal_tools.announcement.exception.AnnouncementAccessDeniedException;
import com.sstinternaltools.sstinternal_tools.announcement.exception.AnnouncementAlreadyDeletedException;
import com.sstinternaltools.sstinternal_tools.announcement.exception.AnnouncementNotFoundException;
import com.sstinternaltools.sstinternal_tools.announcement.model.Announcement;
import com.sstinternaltools.sstinternal_tools.announcement.model.AnnouncementLabel;
import com.sstinternaltools.sstinternal_tools.announcement.repository.AnnouncementRepository;
import com.sstinternaltools.sstinternal_tools.announcement.service.template.AnnouncementService;
import com.sstinternaltools.sstinternal_tools.security.entity.UserPrincipal;
import com.sstinternaltools.sstinternal_tools.user.entity.Role;
import com.sstinternaltools.sstinternal_tools.user.entity.User;
import com.sstinternaltools.sstinternal_tools.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository repo;
    private final UserRepository userRepo;

    AnnouncementServiceImpl(AnnouncementRepository repo,
                            UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    private static final Map<Role, Integer> RANK = Map.of(
            Role.STUDENT_ADMIN, 1,
            Role.ADMIN,         2,
            Role.SUPER_ADMIN,   3);

    private Authentication auth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private UserPrincipal principal() {
        return (UserPrincipal) auth().getPrincipal();
    }

    private int highestRankFromAuthorities(Collection<? extends GrantedAuthority> auths) {
        return auths.stream()
                .map(GrantedAuthority::getAuthority)
                .map(a -> a.replaceFirst("(?i)role_", ""))
                .map(String::toUpperCase)
                .map(name -> {
                    try { return Role.valueOf(name); }
                    catch (IllegalArgumentException e) { return null; }
                })
                .filter(Objects::nonNull)
                .map(RANK::get)
                .max(Integer::compare)
                .orElse(0);
    }

    private int highestRankOfUser(User user) {
        return user.getUserRoles().stream()
                .map(ur -> RANK.getOrDefault(ur.getRole(), 0))
                .max(Integer::compare)
                .orElse(0);
    }

    private boolean canManage(Announcement a) {
        Long callerId = principal().getUser().getId();
        if (Objects.equals(a.getAuthor().getId(), callerId))
            return true;

        int callerRank = highestRankFromAuthorities(auth().getAuthorities());
        int authorRank = highestRankOfUser(a.getAuthor());
        return callerRank > authorRank;
    }

    private AnnouncementResponse map(Announcement a) {
        return new AnnouncementResponse(
                a.getId(),
                a.getTitle(),
                a.getDescription(),
                a.getLabels(),
                a.getAuthor().getEmail(),
                a.getCreatedAt());
    }

    @Override
    public AnnouncementResponse create(AnnouncementRequest dto) {
        User author = userRepo.getReferenceById(principal().getUser().getId());
        Announcement entity = new Announcement(dto.title(), dto.description(),
                dto.labels(), author);
        return map(repo.save(entity));
    }

    @Override
    public Page<AnnouncementResponse> search(Set<AnnouncementLabel> labels, Pageable pageable) {
        Page<Announcement> page =
                (labels == null || labels.isEmpty())
                        ? repo.findDistinctByDeletedFalse(pageable)
                        : repo.findDistinctByLabelsInAndDeletedFalse(labels, pageable);
        return page.map(this::map);
    }

    @Override
    public AnnouncementResponse findById(Long id) {
        Announcement a = repo.findById(id)
                .filter(x -> !x.isDeleted())
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
        return map(a);
    }

    @Override
    public AnnouncementResponse update(Long id, AnnouncementUpdateRequest dto) {

        Announcement a = repo.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));

        if (a.isDeleted())
            throw new AnnouncementAlreadyDeletedException(id);

        if (!canManage(a))
            throw new AnnouncementAccessDeniedException();

        a.update(dto.title(), dto.description(), dto.labels());
        return map(a);
    }

    @Override
    public void delete(Long id) {

        Announcement a = repo.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));

        if (a.isDeleted())
            throw new AnnouncementAlreadyDeletedException(id);

        if (!canManage(a))
            throw new AnnouncementAccessDeniedException();

        a.markDeleted();
    }
}