// src/main/java/com/example/post_creating/exception/InvalidFileTypeException.java
package com.example.post_creating.exception;

public class InvalidFileTypeException extends RuntimeException {
    public InvalidFileTypeException(String message) {
        super(message);
    }
}

