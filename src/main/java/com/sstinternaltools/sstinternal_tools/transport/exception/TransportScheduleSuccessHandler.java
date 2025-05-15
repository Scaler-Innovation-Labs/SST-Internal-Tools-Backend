package com.sstinternaltools.sstinternal_tools.transport.exception;

import com.sstinternaltools.sstinternal_tools.gallery.exception.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TranportScheduleSuccessHandler {

    @ExceptionHandler(TransportScheduleNotFound.class)
    public ResponseEntity<String> handleScheduleNotFound(EventNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
