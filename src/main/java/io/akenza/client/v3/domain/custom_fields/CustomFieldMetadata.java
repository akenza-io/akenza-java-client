package io.akenza.client.v3.domain.custom_fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.custom_fields.objects.CustomFieldMetaType;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomFieldMetadata.class)
@JsonDeserialize(as = ImmutableCustomFieldMetadata.class)
@AkenzaStyle
public interface CustomFieldMetadata extends Versioned, Audited {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

    String workspaceId();

    CustomFieldMetaType type();

    @Nullable
    String description();

    Boolean required();

    @Nullable
    List<String> selectOptions();
}
