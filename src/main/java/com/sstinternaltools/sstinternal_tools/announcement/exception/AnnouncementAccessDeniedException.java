package com.sstinternaltools.sstinternal_tools.announcement.exception;

public class AnnouncementAccessDeniedException extends RuntimeException {

    public AnnouncementAccessDeniedException() {
        super("You are not authorised to manage this announcement");
    }
}
