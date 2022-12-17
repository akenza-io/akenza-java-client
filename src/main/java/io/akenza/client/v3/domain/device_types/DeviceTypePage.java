package io.akenza.client.v3.domain.device_types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceTypePage.class)
@JsonDeserialize(as = ImmutableDeviceTypePage.class)
@AkenzaStyle
public abstract class DeviceTypePage implements Page<DeviceType> {
    public abstract List<DeviceType> content();
}
