package com.sstinternaltools.sstinternal_tools.documents.repository;

import com.sstinternaltools.sstinternal_tools.documents.entity.DocumentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentCategoryRepository extends JpaRepository<DocumentCategory, Long> {
    boolean existsByNormalizedName(String normalizedName);
    boolean existsByNormalizedNameAndIdNot(String normalizedName, Long id);
    @Query("SELECT dc FROM DocumentCategory dc WHERE LOWER(dc.normalizedName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<DocumentCategory> findByNormalizedNameContainingIgnoreCase(@Param("keyword") String keyword);

}
