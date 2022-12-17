package io.akenza.client.v3.domain.custom_fields.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.custom_fields.objects.CustomFieldMetaType;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateCustomFieldMetadataCommand.class)
@JsonDeserialize(as = ImmutableCreateCustomFieldMetadataCommand.class)
@AkenzaStyle
public abstract class CreateCustomFieldMetadataCommand {
    public abstract String workspaceId();

    public abstract String name();

    @Nullable
    public abstract String description();

    public abstract CustomFieldMetaType type();

    @Value.Default
    public Boolean required() {
        return false;
    }

    @Nullable
    public abstract List<String> selectOptions();
}
