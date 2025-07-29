package com.sstinternaltools.sstinternal_tools.transport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sstinternaltools.sstinternal_tools.transport.entity.BusStatus;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;

public class BusScheduleUpdateDto {

    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private BusStatus busStatus;
    private int studentsBoarded;

    public BusStatus getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(BusStatus busStatus) {
        this.busStatus = busStatus;
    }

    public int getStudentsBoarded() {
        return studentsBoarded;
    }

    public void setStudentsBoarded(int studentsBoarded) {
        this.studentsBoarded = studentsBoarded;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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
