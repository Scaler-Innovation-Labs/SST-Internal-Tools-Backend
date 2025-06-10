package com.sstinternaltools.sstinternal_tools.documents.repository;

import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentVersionRepository extends JpaRepository<DocumentVersion, Long> {
}
