package io.akenza.client.v3.domain.devices.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Preconditions;
import io.akenza.client.v3.domain.common.Connectivity;
import io.akenza.client.v3.domain.devices.objects.LoRaProperties;
import io.akenza.client.v3.domain.devices.objects.TagReference;
import io.akenza.client.v3.domain.devices.objects.UpsertCustomField;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceCommand.class)
@AkenzaStyle
public abstract class CreateDeviceCommand {
    /**
     * Name
     */
    public abstract String name();

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Unique device ID (e.g. hardware identifier)
     */
    public abstract String deviceId();

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
    public abstract String workspaceId();

    /**
     * Data Flow ID
     */
    public abstract String dataFlowId();

    /**
     * Connectivity
     */
    public abstract Connectivity connectivity();

    /**
     * LoRa Properties
     */
    @Nullable
    public abstract LoRaProperties loRaProperties();

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
        Preconditions.checkState(!connectivity().equals(Connectivity.LORA) || loRaProperties() != null, "LoRa properties must be set when creating LoRa devices");
    }
}
