package io.akenza.client.http;


import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Preconditions;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@AkenzaStyle
public abstract class AuthOptions {
    @Nullable
    public abstract String bearerToken();

    @Nullable
    public abstract String apiKey();

    @Value.Check
    protected void check() {
        Preconditions.checkState(bearerToken() != null || apiKey() != null, "either apiKey or bearerToken must be set");
    }
}
