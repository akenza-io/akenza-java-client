package io.akenza.client.v3.domain.custom_fields.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateCustomFieldMetadataCommand.class)
@JsonDeserialize(as = ImmutableUpdateCustomFieldMetadataCommand.class)
@AkenzaStyle
public abstract class UpdateCustomFieldMetadataCommand {
    /**
     * ID
     */
    public abstract String id();
    
    public abstract String name();

    @Nullable
    public abstract String description();

    public abstract Boolean required();

    @Nullable
    public abstract List<String> selectOptions();
}
