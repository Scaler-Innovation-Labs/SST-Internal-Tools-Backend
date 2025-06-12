package com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentCategorySummaryDto {
    @NotBlank(message = "Category name cannot be null")
    private String name;
    @NotBlank(message = "Category description cannot be null")
    private String description;

    public DocumentCategorySummaryDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DocumentCategorySummaryDto() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
