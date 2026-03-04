package com.Fotbal.football_backend.exception;

import lombok.Data;

import java.time.Instant;

@Data
public class ExceptionBody {

    private String message;
    private Instant timestamp;

    public ExceptionBody(String message) {
        this.message = message;
        this.timestamp = Instant.now();
    }
}

