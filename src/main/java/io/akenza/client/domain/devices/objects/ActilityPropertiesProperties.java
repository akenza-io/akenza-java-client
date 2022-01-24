package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.devices.objects.enums.ActilityMotionIndicator;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableActilityPropertiesProperties.class)
@JsonDeserialize(as = ImmutableActilityPropertiesProperties.class)
public interface ActilityPropertiesProperties {
    String actilityId();

    String connectivityPlanId();

    String deviceProfileId();

    ActilityMotionIndicator motionIndicator();

    @Nullable
    String routingProfileId();
}
