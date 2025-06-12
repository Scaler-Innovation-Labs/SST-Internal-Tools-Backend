package com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentCategorySummaryDto {
    @NotBlank(message = "Category name cannot be null")
    private String name;

    public DocumentCategorySummaryDto(String name) {
        this.name = name;
    }

    public DocumentCategorySummaryDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
