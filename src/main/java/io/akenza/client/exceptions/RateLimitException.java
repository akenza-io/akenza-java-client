package io.akenza.client.exceptions;

public class RateLimitException extends AkenzaException {
    private long limit;
    private long remaining;
    private long reset;

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
