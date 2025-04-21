package com.sstinternaltools.sstinternal_tools.announcement.service.persistence.implementation;


import com.sstinternaltools.sstinternal_tools.announcement.entity.Announcement;
import com.sstinternaltools.sstinternal_tools.announcement.entity.LabelType;
import com.sstinternaltools.sstinternal_tools.announcement.repository.AnnouncementRepository;
import com.sstinternaltools.sstinternal_tools.announcement.service.persistence.template.AnnouncementPersistenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnnouncementPersistenceServiceImpl implements AnnouncementPersistenceService {

    private final AnnouncementRepository repository;

    public AnnouncementPersistenceServiceImpl(AnnouncementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Announcement save(Announcement announcement) {
        return repository.save(announcement);
    }

    @Override
    public Optional<Announcement> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Announcement> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Announcement> findByLabelType(LabelType labelType) {
        return repository.findByLabels_LabelType(labelType);
    }
}

