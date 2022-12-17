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
@JsonSerialize(as = ImmutableUpdateCustomLogicBlockCommand.class)
@JsonDeserialize(as = ImmutableUpdateCustomLogicBlockCommand.class)
@AkenzaStyle
public interface UpdateCustomLogicBlockCommand {
    String id();
    
    String name();

    @Nullable
    String description();

    List<CustomLogicDataSource> dataSources();

    List<CustomLogicProperty> properties();

    String script();
}
