package io.akenza.client.exceptions;

public class RateLimitException extends AkenzaException {
    private final long limit;
    private final long remaining;
    private final long reset;

    public RateLimitException(long limit, long remaining, long reset) {
        super();
        this.limit = limit;
        this.remaining = remaining;
        this.reset = reset;
    }

    public long getLimit() {
        return limit;
    }

    public long getRemaining() {
        return remaining;
    }

    public long getReset() {
        return reset;
    }
}
