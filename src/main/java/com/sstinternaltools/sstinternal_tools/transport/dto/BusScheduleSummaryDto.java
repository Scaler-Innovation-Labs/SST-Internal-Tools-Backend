package com.sstinternaltools.sstinternal_tools.transport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class BusScheduleSummaryDto {

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
    @NotNull(message="Bus Status cannot be blank")
    private BusStatus busStatus;

    public BusScheduleSummaryDto(String source, String destination, LocalTime departureTime,LocalTime arrivalTime,DayOfWeek dayofWeek, LocalDate date,BusStatus busStatus) {
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.dayofWeek = dayofWeek;
        this.date = date;
        this.busStatus = busStatus;
    }

    public BusScheduleSummaryDto() {}

    public BusStatus getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(BusStatus busStatus) {
        this.busStatus = busStatus;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime( LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public DayOfWeek getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek( DayOfWeek dayofWeek) {
        this.dayofWeek = dayofWeek;
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
