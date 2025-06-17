package com.sstinternaltools.sstinternal_tools.RoomBooking.dto;

import java.time.LocalDateTime;

public class BookingResponseDTO {
    private Long bookingId;
    private Long roomId;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    // Constructors
    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long bookingId, Long roomId, Long userId, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.userId = userId;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
