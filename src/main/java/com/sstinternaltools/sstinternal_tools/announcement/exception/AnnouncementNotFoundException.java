package com.sstinternaltools.sstinternal_tools.announcement.exception;

public class AnnouncementNotFoundException extends RuntimeException {

    public AnnouncementNotFoundException(Long id) {
        super("Announcement " + id + " not found");
    }
}
