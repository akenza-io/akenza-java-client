package io.akenza.client.v3.domain.device_credentials;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.v3.domain.device_credentials.objects.Algorithm;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceCredential.class)
@JsonDeserialize(as = ImmutableDeviceCredential.class)
@AkenzaStyle
public interface DeviceCredential extends Audited {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    @Nullable
    String name();

    /**
     * Akenza Device ID
     */
    String akenzaDeviceId();

    /**
     * Unique Device ID
     */
    String deviceId();

    /**
     * Algorithm
     */
    Algorithm algorithm();

    /**
     * Public Key
     */
    String publicKey();

    /**
     * Fingerprint
     */
    String fingerprint();

    /**
     * Expiration
     */
    @Nullable
    Instant expiration();
}
