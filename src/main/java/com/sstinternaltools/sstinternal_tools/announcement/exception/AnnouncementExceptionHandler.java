package com.sstinternaltools.sstinternal_tools.announcement.exception;

import com.sstinternaltools.sstinternal_tools.announcement.controller.AnnouncementController;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice(basePackageClasses = AnnouncementController.class)
public class AnnouncementExceptionHandler {

    @ExceptionHandler(AnnouncementNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(AnnouncementNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(AnnouncementAlreadyDeletedException.class)
    public ResponseEntity<Map<String, Object>> handleGone(AnnouncementAlreadyDeletedException ex) {
        return buildErrorResponse(HttpStatus.GONE, ex.getMessage());
    }

    @ExceptionHandler(AnnouncementAccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleForbidden(AnnouncementAccessDeniedException ex) {
        return buildErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> responseBody = Map.of(
                "timestamp", Instant.now().toString(),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        );
        return new ResponseEntity<>(responseBody, status);
    }
}