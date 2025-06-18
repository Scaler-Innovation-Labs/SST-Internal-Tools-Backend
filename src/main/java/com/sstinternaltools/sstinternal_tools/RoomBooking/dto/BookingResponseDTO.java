package com.sstinternaltools.sstinternal_tools.RoomBooking.dto;

import java.time.LocalDateTime;

public class BookingResponseDTO {
    private Long bookingId;
    private Long roomId;
    private Long userId;
    private Long adminId; // Admin who approved/rejected the room req
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private LocalDateTime requestTime;
    private LocalDateTime approvalTime;

    // Constructors
    public BookingResponseDTO() {
    }

    public BookingResponseDTO(Long bookingId, Long roomId, Long userId, Long adminId, LocalDateTime startTime, LocalDateTime endTime, String status, LocalDateTime requestTime, LocalDateTime approvalTime) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.userId = userId;
        this.adminId = adminId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.requestTime = requestTime;
        this.approvalTime = approvalTime;
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

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
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

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }
}
