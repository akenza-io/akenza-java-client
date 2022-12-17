package io.akenza.client.v3.domain.tags.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateTagCommand.class)
@JsonDeserialize(as = ImmutableCreateTagCommand.class)
@AkenzaStyle
public abstract class CreateTagCommand {
    /**
     * Name
     */
    public abstract String name();

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Workspace ID
     */
    public abstract String workspaceId();

    @Nullable
    public abstract String color();
}
