package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.v3.domain.custom_fields.objects.CustomFieldMetaType;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalCustomFieldMeta.class)
@JsonDeserialize(as = ImmutableMinimalCustomFieldMeta.class)
@AkenzaStyle
public interface MinimalCustomFieldMeta extends Audited {
    String id();

    String workspaceId();

    String name();

    CustomFieldMetaType type();
}
