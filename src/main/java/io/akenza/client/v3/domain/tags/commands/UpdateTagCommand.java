package io.akenza.client.v3.domain.tags.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateTagCommand.class)
@JsonDeserialize(as = ImmutableUpdateTagCommand.class)
@AkenzaStyle
public abstract class UpdateTagCommand {
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
    
    @Nullable
    public abstract String color();
}