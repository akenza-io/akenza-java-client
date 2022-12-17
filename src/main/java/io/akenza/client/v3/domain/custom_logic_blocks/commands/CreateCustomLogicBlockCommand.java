package io.akenza.client.v3.domain.custom_logic_blocks.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.CustomLogicDataSource;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.CustomLogicProperty;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateCustomLogicBlockCommand.class)
@JsonDeserialize(as = ImmutableCreateCustomLogicBlockCommand.class)
@AkenzaStyle
public interface CreateCustomLogicBlockCommand {
    String organizationId();

    String name();

    @Nullable
    String description();

    List<CustomLogicDataSource> dataSources();

    List<CustomLogicProperty> properties();

    String script();
}
