package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableGpsCoordinates.class)
@JsonDeserialize(as = ImmutableGpsCoordinates.class)
@AkenzaStyle
public interface GpsCoordinates {
    /**
     * Latitude
     */
    @Nullable
    Double latitude();

    /**
     * Longitude
     */
    @Nullable
    Double longitude();
}
