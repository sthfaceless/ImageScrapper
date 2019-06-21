package ru.projects.prog_ja.exceptions;

public class UnknownFormatException extends Exception {

    public UnknownFormatException() {
        super();
    }

    public UnknownFormatException(String message) {
        super(message);
    }

    public UnknownFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownFormatException(Throwable cause) {
        super(cause);
    }

    protected UnknownFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
