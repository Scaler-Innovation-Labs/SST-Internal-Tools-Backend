package com.sstinternaltools.sstinternal_tools.RoomBooking.dto;

public class RoomDTO {
    private Long roomId;
    private String roomName;
    private Integer capacity;
    private boolean available;

    // Constructors
    public RoomDTO() {
    }

    public RoomDTO(Long roomId, String roomName, Integer capacity, boolean available) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.available = available;
    }

    // Getters and Setters
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
