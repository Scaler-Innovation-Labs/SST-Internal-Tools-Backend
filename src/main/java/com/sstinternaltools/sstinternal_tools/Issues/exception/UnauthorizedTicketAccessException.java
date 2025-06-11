package com.sstinternaltools.sstinternal_tools.Issues.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedTicketAccessException extends RuntimeException {

    public UnauthorizedTicketAccessException(String message) {
        super(message);
    }

    public UnauthorizedTicketAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
