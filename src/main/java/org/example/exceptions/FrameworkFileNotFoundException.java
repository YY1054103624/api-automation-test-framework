package org.example.exceptions;

public class FrameworkFileNotFoundException extends FrameworkException{
    public FrameworkFileNotFoundException() {
        super();
    }

    public FrameworkFileNotFoundException(String message) {
        super(message);
    }

    public FrameworkFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkFileNotFoundException(Throwable cause) {
        super(cause);
    }
}
