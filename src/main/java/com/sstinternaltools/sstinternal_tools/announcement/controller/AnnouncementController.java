package com.sstinternaltools.sstinternal_tools.announcement.controller;

import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementRequest;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementResponse;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementUpdateRequest;
import com.sstinternaltools.sstinternal_tools.announcement.model.AnnouncementLabel;
import com.sstinternaltools.sstinternal_tools.announcement.service.template.AnnouncementService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    private final AnnouncementService svc;

    public AnnouncementController(AnnouncementService svc) {
        this.svc = svc;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public AnnouncementResponse post(@RequestBody AnnouncementRequest dto) {
        return svc.create(dto);
    }

    @GetMapping
    public Page<AnnouncementResponse> list(
            @RequestParam(required = false) Set<AnnouncementLabel> labels,
            Pageable pageable) {
        return svc.search(labels, pageable);
    }

    @GetMapping("/{id}")
    public AnnouncementResponse get(@PathVariable Long id) {
        return svc.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public AnnouncementResponse put(@PathVariable Long id,
                                    @RequestBody AnnouncementUpdateRequest dto) {
        return svc.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }
}
