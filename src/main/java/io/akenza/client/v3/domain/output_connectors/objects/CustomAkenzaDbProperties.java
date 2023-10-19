package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Preconditions;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomAkenzaDbProperties.class)
@JsonDeserialize(as = ImmutableCustomAkenzaDbProperties.class)
@AkenzaStyle
public abstract class CustomAkenzaDbProperties {
    public abstract String topic();

    @Nullable
    public abstract String targetDeviceId();

    public abstract Boolean useCurrentDevice();

    @Value.Check
    protected void check() {
        Preconditions.checkState(Boolean.TRUE.equals(useCurrentDevice()) && targetDeviceId() == null, "Target device id should only be set if not the current device is used.");
    }
}
