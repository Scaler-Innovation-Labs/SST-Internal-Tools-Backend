package com.sstinternaltools.sstinternal_tools.transport.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public class BusScheduleUpdateDto {

    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalDate date;

    public BusScheduleUpdateDto(String source, String destination, LocalTime departureTime, LocalDate date) {
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.date = date;
    }

    public BusScheduleUpdateDto() {}

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
