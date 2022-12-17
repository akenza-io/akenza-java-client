package io.akenza.client.v3.domain.device_configuration.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceConfigurationCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceConfigurationCommand.class)
@AkenzaStyle
public interface CreateDeviceConfigurationCommand {
    String workspaceId();

    Map<String, Object> configuration();
}
