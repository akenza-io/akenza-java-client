package io.akenza.client.domain.devices.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableAssignTagCommand.class)
@JsonDeserialize(as = ImmutableAssignTagCommand.class)
public interface AssignTagCommand {
    /**
     * ID
     */
    String id();
}
