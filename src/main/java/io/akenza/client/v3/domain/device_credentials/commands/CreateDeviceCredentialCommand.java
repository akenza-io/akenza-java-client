package io.akenza.client.v3.domain.device_credentials.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.device_credentials.objects.Algorithm;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceCredentialCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceCredentialCommand.class)
@AkenzaStyle
public abstract class CreateDeviceCredentialCommand {
    /**
     * Name
     */
    @Nullable
    public abstract String name();

    /**
     * Akenza Device ID
     */
    public abstract String akenzaDeviceId();

    /**
     * Algorithm
     */
    public abstract Algorithm algorithm();

    /**
     * Public Key
     */
    public abstract String publicKey();

    /**
     * Expiration
     */
    @Nullable
    public abstract Instant expiration();
}
