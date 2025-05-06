package com.sstinternaltools.sstinternal_tools.announcement.exception;

import com.sstinternaltools.sstinternal_tools.announcement.controller.AnnouncementController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice(basePackageClasses = AnnouncementController.class)
public class AnnouncementExceptionHandler {

    @ExceptionHandler(AnnouncementNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(AnnouncementNotFoundException ex) {
        return body(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(AnnouncementAlreadyDeletedException.class)
    @ResponseStatus(HttpStatus.GONE)
    public Map<String, Object> handleGone(AnnouncementAlreadyDeletedException ex) {
        return body(HttpStatus.GONE, ex.getMessage());
    }

    @ExceptionHandler(AnnouncementAccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleForbidden(AnnouncementAccessDeniedException ex) {
        return body(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    private Map<String, Object> body(HttpStatus status, String msg) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status",    status.value(),
                "error",     status.getReasonPhrase(),
                "message",   msg);
    }
}