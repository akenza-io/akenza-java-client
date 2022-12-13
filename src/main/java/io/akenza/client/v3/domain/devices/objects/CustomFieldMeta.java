package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Audited;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomFieldMeta.class)
@JsonDeserialize(as = ImmutableCustomFieldMeta.class)
public interface CustomFieldMeta extends Audited {
    String id();

    String workspaceId();

    String name();

    CustomFieldMetaType type();
}
