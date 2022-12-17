package io.akenza.client.v3.domain.device_types.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.device_types.objects.DeviceTypeMetadata;
import io.akenza.client.v3.domain.device_types.objects.Script;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceTypeCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceTypeCommand.class)
@AkenzaStyle
public interface CreateDeviceTypeCommand {
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
    String organizationId();

    /**
     * Type must be set to manual
     */
    default String type() {
        return "manual";
    }

    /**
     * Device Type Metadata
     */
    DeviceTypeMetadata meta();

    /**
     * Device type schema per topic
     */
    @Nullable
    Map<String, Object> schemas();

    /**
     * Device type uplink decoder
     */
    @Nullable
    Script uplinkScript();

    /**
     * Device type downlink encoder
     */
    @Nullable
    Script downlinkScript();

    /**
     * Device type picture url
     */
    @Nullable
    String pictureUrl();
}
