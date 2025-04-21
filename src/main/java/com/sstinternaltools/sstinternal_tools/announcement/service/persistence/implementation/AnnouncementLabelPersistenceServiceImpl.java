package com.sstinternaltools.sstinternal_tools.announcement.service.persistence.implementation;
import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabel;
import com.sstinternaltools.sstinternal_tools.announcement.entity.AnnouncementLabelId;
import com.sstinternaltools.sstinternal_tools.announcement.repository.AnnouncementLabelRepository;
import com.sstinternaltools.sstinternal_tools.announcement.service.persistence.template.AnnouncementLabelPersistenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnnouncementLabelPersistenceServiceImpl implements AnnouncementLabelPersistenceService {

    private final AnnouncementLabelRepository repository;

    public AnnouncementLabelPersistenceServiceImpl(AnnouncementLabelRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnnouncementLabel save(AnnouncementLabel label) {
        return repository.save(label);
    }

    @Override
    public void deleteById(AnnouncementLabelId id) {
        repository.deleteById(id);
    }
}

