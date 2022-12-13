package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateActilityProperties.class)
@JsonDeserialize(as = ImmutableUpdateActilityProperties.class)
@AkenzaStyle
public interface UpdateActilityProperties {
    /**
     * Actility ID
     */
    String actilityId();

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
    ActilityMotionIndicator motionIndicator();

    /**
     * Routing Profile ID
     */
    String routingProfileId();
}
