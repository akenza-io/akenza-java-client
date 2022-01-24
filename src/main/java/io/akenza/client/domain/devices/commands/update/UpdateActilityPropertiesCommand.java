package io.akenza.client.domain.devices.commands.update;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.devices.objects.enums.ActilityMotionIndicator;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateActilityPropertiesCommand.class)
@JsonDeserialize(as = ImmutableUpdateActilityPropertiesCommand.class)
public interface UpdateActilityPropertiesCommand {
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
