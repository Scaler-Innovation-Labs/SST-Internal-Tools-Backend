package com.sstinternaltools.sstinternal_tools.transport.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class BusSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_schedule_id",nullable = false)
    private Long id;
    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    @Enumerated(EnumType.STRING)
    private BusStatus busStatus;
    private LocalDate date;

    public BusSchedule(String source, String destination, LocalTime departureTime,LocalTime arrivalTime, LocalDate date,BusStatus busStatus) {
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.busStatus = busStatus;
    }

    public BusSchedule() {}

    public BusStatus getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(BusStatus busStatus) {
        this.busStatus = busStatus;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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

