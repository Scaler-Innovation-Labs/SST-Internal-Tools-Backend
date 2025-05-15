package com.sstinternaltools.sstinternal_tools.gallery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class EventCreateDto {
    @NotBlank(message = "Event name cannot be blank")
    private String name;
    @NotBlank(message = "Event description cannot be blank")
    private String description;
    @NotNull(message = "Event date cannot be blank")
    private LocalDate date;
    @NotBlank(message = "Drive link cannot be blank")
    private String driveLink;

    public EventCreateDto(String name, String description, LocalDate date, String driveLink) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.driveLink = driveLink;
    }

    public EventCreateDto() {}

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDriveLink() {
        return driveLink;
    }

    public void setDriveLink(String driveLink) {
        this.driveLink = driveLink;
    }
}
