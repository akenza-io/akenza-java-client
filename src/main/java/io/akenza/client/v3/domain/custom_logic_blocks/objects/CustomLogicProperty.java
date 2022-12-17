package io.akenza.client.v3.domain.custom_logic_blocks.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.rules.objects.ValueType;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomLogicProperty.class)
@JsonDeserialize(as = ImmutableCustomLogicProperty.class)
@AkenzaStyle
public interface CustomLogicProperty {
    String name();

    @Nullable
    String description();

    String variableName();

    @Nullable
    Object defaultValue();

    ValueType type();
}
