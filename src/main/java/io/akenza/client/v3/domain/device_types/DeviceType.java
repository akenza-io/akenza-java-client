package io.akenza.client.v3.domain.device_types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.v3.domain.device_types.objects.DeviceTypeMetadata;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Versioned;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceType.class)
@JsonDeserialize(as = ImmutableDeviceType.class)
@AkenzaStyle
public interface DeviceType extends Versioned {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

    /**
     * Description
     */
    @Nullable
    String description();

    /**
     * Organization Id
     */
    @Nullable
    String organizationId();

    /**
     * Type (manual or library)
     */
    String type();


    /**
     * Device Type Metadata
     */
    DeviceTypeMetadata meta();
}
