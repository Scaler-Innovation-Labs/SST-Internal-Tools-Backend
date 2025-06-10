package com.sstinternaltools.sstinternal_tools.documents.repository;

import com.sstinternaltools.sstinternal_tools.documents.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
