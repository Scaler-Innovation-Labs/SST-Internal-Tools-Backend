package com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentCategoryResponseDto {
    @NotNull(message="Category Id cannot be null")
    private Long id;
    @NotBlank(message = "Category name cannot be null")
    private String name;

    public DocumentCategoryResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DocumentCategoryResponseDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
