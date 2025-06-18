package com.sstinternaltools.sstinternal_tools.RoomBooking.dto;

public class BookingStatusDTO {
    private Long bookingId;
    private String status;

    // Constructors
    public BookingStatusDTO() {
    }

    public BookingStatusDTO(Long bookingId, String status) {
        this.bookingId = bookingId;
        this.status = status;
    }

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
