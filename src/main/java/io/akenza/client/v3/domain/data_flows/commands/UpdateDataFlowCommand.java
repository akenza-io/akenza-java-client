package io.akenza.client.v3.domain.data_flows.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateDataFlowCommand.class)
@JsonDeserialize(as = ImmutableUpdateDataFlowCommand.class)
@AkenzaStyle
public abstract class UpdateDataFlowCommand {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

    /**
     * Description
     */
    @Nullable
    public abstract String description();
}
