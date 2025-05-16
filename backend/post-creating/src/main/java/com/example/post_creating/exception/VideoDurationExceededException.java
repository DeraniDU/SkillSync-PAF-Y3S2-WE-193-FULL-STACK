package com.example.post_creating.exception;

public class VideoDurationExceededException extends RuntimeException {
    public VideoDurationExceededException(String message) {
        super(message);
    }
}
