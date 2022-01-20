package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableGpsCoordinates.class)
@JsonDeserialize(as = ImmutableGpsCoordinates.class)
public interface GpsCoordinates {
    /**
     * Latitude
     */
    double latitude();

    /**
     * Longitude
     */
    double longitude();
}
