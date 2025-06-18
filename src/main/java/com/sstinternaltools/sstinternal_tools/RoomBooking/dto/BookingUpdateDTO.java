package com.sstinternaltools.sstinternal_tools.RoomBooking.dto;

import java.time.LocalDateTime;

public class BookingUpdateDTO {
    private Long bookingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    // Constructors
    public BookingUpdateDTO() {
    }

    public BookingUpdateDTO(Long bookingId, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.bookingId = bookingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
