package org.example.exceptions;

public class FrameworkException extends RuntimeException{
    public FrameworkException() {
        super();
    }
    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkException(Throwable cause) {
        super(cause);
    }
}
