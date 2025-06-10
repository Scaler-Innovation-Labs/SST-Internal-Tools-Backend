package com.sstinternaltools.sstinternal_tools.documents.repository;

import com.sstinternaltools.sstinternal_tools.documents.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
