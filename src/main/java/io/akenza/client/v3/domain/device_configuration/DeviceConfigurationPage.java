package io.akenza.client.v3.domain.device_configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceConfigurationPage.class)
@JsonDeserialize(as = ImmutableDeviceConfigurationPage.class)
@AkenzaStyle
public abstract class DeviceConfigurationPage implements Page<DeviceConfiguration> {
    public abstract List<DeviceConfiguration> content();
}
