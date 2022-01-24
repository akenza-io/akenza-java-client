package io.akenza.client.domain.assets.objects;

import io.akenza.client.domain.assets.objects.enums.AssetType;
import io.akenza.client.domain.devices.objects.CustomField;
import io.akenza.client.domain.devices.objects.Tag;
import io.akenza.client.utils.Audited;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public interface Asset extends Audited {
    String id();

    String name();

    @Nullable
    String description();

    /**
     * only available on get requests
     */
    @Nullable
    AssetType type();

    String workspaceId();

    String organizationId();

    @Nullable
    String integrationId();

    /**
     * Integration
     * only available on get requests
     */
    @Nullable
    Integration integration();

    @Value.Default
    default List<CustomField> customFields() {
        return new ArrayList<>();
    }

    @Value.Default
    default List<Tag> tags() {
        return new ArrayList<>();
    }

    /**
     * Uplink Metrics
     * only available on get requests
     */
    @Nullable
    UplinkMetrics uplinkMetrics();
}
