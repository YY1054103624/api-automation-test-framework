package org.example.exceptions;

public class FrameworkFileIOException extends FrameworkException{
    public FrameworkFileIOException() {

    }

    public FrameworkFileIOException(String message) {
        super(message);
    }

    public FrameworkFileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FrameworkFileIOException(Throwable cause) {
        super(cause);
    }
}
