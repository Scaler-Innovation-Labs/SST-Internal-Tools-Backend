package com.sstinternaltools.sstinternal_tools.documents.repository;

import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentCategoryRepository extends JpaRepository<DocumentCategory, Long> {
}
