package com.sstinternaltools.sstinternal_tools.announcement.controller;

import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementRequest;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementResponse;
import com.sstinternaltools.sstinternal_tools.announcement.dto.AnnouncementUpdateRequest;
import com.sstinternaltools.sstinternal_tools.announcement.model.AnnouncementLabel;
import com.sstinternaltools.sstinternal_tools.announcement.service.template.AnnouncementService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<AnnouncementResponse> post(@Valid @RequestBody AnnouncementRequest dto) {
        AnnouncementResponse response = svc.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<AnnouncementResponse>> list(
            @RequestParam(required = false) Set<AnnouncementLabel> labels,
            Pageable pageable) {
        Page<AnnouncementResponse> responses = svc.search(labels, pageable);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementResponse> get(@PathVariable Long id) {
        AnnouncementResponse response = svc.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<AnnouncementResponse> put(@PathVariable Long id,
                                                    @RequestBody AnnouncementUpdateRequest dto) {
        AnnouncementResponse response = svc.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAnyRole('STUDENT_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
