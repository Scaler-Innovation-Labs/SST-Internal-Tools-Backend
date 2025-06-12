package com.sstinternaltools.sstinternal_tools.documents.dto.documentCategoryDto;

public class DocumentCategoryUpdateDto {
    String name;
    String description;

    public DocumentCategoryUpdateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DocumentCategoryUpdateDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
