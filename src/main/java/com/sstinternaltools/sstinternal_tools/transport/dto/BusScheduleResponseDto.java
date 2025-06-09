package com.sstinternaltools.sstinternal_tools.transport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class BusScheduleResponseDto {
    @NotBlank(message = "Id cannot be blank")
    private Long id;
    @NotBlank(message = "Source cannot be blank")
    private String source;
    @NotBlank(message = "Destination cannot be blank")
    private String destination;
    @NotNull(message = "Departure time cannot be blank")
    private LocalTime departureTime;
    @NotNull(message = "Arrival time cannot be blank")
    private LocalTime arrivalTime;
    @NotNull(message = "Day cannot be blank")
    private DayOfWeek dayofWeek;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date cannot be blank")
    private LocalDate date;

    public BusScheduleResponseDto(Long id, String source, String destination, LocalTime departureTime,LocalTime arrivalTime,DayOfWeek dayofWeek, LocalDate date) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.dayofWeek = dayofWeek;
        this.date = date;
    }

    public BusScheduleResponseDto() {}

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime( LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public DayOfWeek getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(DayOfWeek dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
