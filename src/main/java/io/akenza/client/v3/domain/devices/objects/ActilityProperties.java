package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableActilityProperties.class)
@JsonDeserialize(as = ImmutableActilityProperties.class)
public interface ActilityProperties {
    String actilityId();

    String connectivityPlanId();

    String deviceProfileId();

    ActilityMotionIndicator motionIndicator();

    @Nullable
    String routingProfileId();
}
