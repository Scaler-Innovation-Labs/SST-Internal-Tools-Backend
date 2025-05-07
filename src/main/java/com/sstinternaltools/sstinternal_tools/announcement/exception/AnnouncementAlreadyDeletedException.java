package com.sstinternaltools.sstinternal_tools.announcement.exception;

public class AnnouncementAlreadyDeletedException extends RuntimeException {

    public AnnouncementAlreadyDeletedException(Long id) {
        super("Announcement " + id + " is already deleted");
    }
}
