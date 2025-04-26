package com.sstinternaltools.sstinternal_tools.transport.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class BusScheduleCreateDto {
    @NotBlank(message = "Source cannot be blank")
    private String source;
    @NotBlank(message = "Destination cannot be blank")
    private String destination;
    @NotNull(message = "Departure time cannot be blank")
    private LocalTime departureTime;
    @NotNull(message = "Date cannot be blank")
    private LocalDate date;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
