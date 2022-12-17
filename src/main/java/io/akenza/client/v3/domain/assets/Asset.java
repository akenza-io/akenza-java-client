package io.akenza.client.v3.domain.assets;

import io.akenza.client.utils.Audited;
import io.akenza.client.v3.domain.assets.objects.AssetType;
import io.akenza.client.v3.domain.assets.objects.Integration;
import io.akenza.client.v3.domain.assets.objects.UplinkMetrics;
import io.akenza.client.v3.domain.devices.objects.CustomField;
import io.akenza.client.v3.domain.devices.objects.MinimalTag;
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
    default List<MinimalTag> tags() {
        return new ArrayList<>();
    }

    /**
     * Uplink Metrics
     * only available on get requests
     */
    @Nullable
    UplinkMetrics uplinkMetrics();
}
