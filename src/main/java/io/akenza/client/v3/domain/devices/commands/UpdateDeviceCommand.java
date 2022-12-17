package io.akenza.client.v3.domain.devices.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.common.Connectivity;
import io.akenza.client.v3.domain.devices.objects.LoRaProperties;
import io.akenza.client.v3.domain.devices.objects.TagReference;
import io.akenza.client.v3.domain.devices.objects.UpsertCustomField;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateDeviceCommand.class)
@JsonDeserialize(as = ImmutableUpdateDeviceCommand.class)
@AkenzaStyle
public abstract class UpdateDeviceCommand {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

    /**
     * device ID
     */
    @Nullable
    public abstract String deviceId();

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Tags
     */
    @Nullable
    public abstract List<TagReference> tags();

    /**
     * Custom Fields
     */
    @Nullable
    public abstract List<UpsertCustomField> customFields();

    /**
     * Workspace ID
     */
    @Nullable
    public abstract LoRaProperties loraProperties();

    /**
     * Data Flow ID
     */
    @Nullable
    public abstract String dataFlowId();

    /**
     * Integration ID
     */
    @Nullable
    public abstract String integrationId();

    /**
     * Connectivity
     */
    public abstract Connectivity connectivity();

    /**
     * Additional Properties
     */
    @Nullable
    public abstract Map<String, Object> properties();

    /**
     * Online timeout
     */
    @Nullable
    public abstract Integer onlineTimeout();

    /**
     * Timezone offset
     */
    @Nullable
    public abstract String timezoneOffset();

    @Value.Check
    protected void check() {
        Preconditions.checkState(!(integrationId() == null && dataFlowId() == null)
                || (integrationId() != null && dataFlowId() != null), "Ether Data Flow ID or Integration ID have to be set");
    }
}
