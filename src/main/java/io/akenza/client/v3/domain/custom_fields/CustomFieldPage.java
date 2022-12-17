package io.akenza.client.v3.domain.custom_fields;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomFieldPage.class)
@JsonDeserialize(as = ImmutableCustomFieldPage.class)
@AkenzaStyle
public abstract class CustomFieldPage implements Page<CustomFieldMetadata> {
    public abstract List<CustomFieldMetadata> content();
}
