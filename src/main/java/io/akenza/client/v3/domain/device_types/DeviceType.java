package io.akenza.client.v3.domain.device_types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.device_types.objects.DeviceTypeMetadata;
import io.akenza.client.v3.domain.device_types.objects.Script;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceType.class)
@JsonDeserialize(as = ImmutableDeviceType.class)
@AkenzaStyle
public interface DeviceType extends Versioned, Audited {
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
     * Path in the library (identifier for library types)
     */
    @Nullable
    String libraryPath();

    /**
     * Device type picture url
     */
    @Nullable
    String pictureUrl();
}
