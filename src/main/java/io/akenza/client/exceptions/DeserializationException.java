package io.akenza.client.exceptions;

/**
 * Marks deserialization issues
 */
public class DeserializationException extends AkenzaException {
    public DeserializationException(final String data, final String msg) {
        super("Failed deserializing: " + data + ":\n" + msg);
    }

    public DeserializationException(final String data) {
        super("Failed deserializing: " + data);
    }
}
