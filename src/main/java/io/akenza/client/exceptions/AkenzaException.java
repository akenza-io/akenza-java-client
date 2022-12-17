package io.akenza.client.exceptions;

public class AkenzaException extends RuntimeException {
    public AkenzaException() {
    }

    public AkenzaException(String message) {
        super(message);
    }

    public AkenzaException(String message, Throwable cause) {
        super(message, cause);
    }

    public AkenzaException(Throwable cause) {
        super(cause);
    }

    public AkenzaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
