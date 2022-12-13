package io.akenza.client.v3.domain.device_types.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceTypeCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceTypeCommand.class)
@AkenzaStyle
public interface CreateDeviceTypeCommand {
    /**
     * Name
     */
    String name();
}
