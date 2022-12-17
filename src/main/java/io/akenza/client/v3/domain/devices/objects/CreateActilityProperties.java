package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateActilityProperties.class)
@JsonDeserialize(as = ImmutableCreateActilityProperties.class)
@AkenzaStyle
public interface CreateActilityProperties {
    /**
     * Connectivity Plan ID
     */
    String connectivityPlanId();

    /**
     * Device Profile ID
     */
    String deviceProfileId();

    /**
     * Motion Indicator
     */
    @Value.Default
    default ActilityMotionIndicator motionIndicator() {
        return ActilityMotionIndicator.NEAR_STATIC;
    }

    /**
     * Routing Profile ID
     */
    @Nullable
    String routingProfileId();
}
