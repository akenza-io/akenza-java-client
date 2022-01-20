package io.akenza.client.domain.devices.commands.update;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import io.akenza.client.domain.devices.commands.AssignTagCommand;
import io.akenza.client.domain.devices.commands.UpsertCustomFieldValueCommand;
import io.akenza.client.domain.devices.objects.enums.Connectivity;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateDeviceCommand.class)
@JsonDeserialize(as = ImmutableUpdateDeviceCommand.class)
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
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Tags
     */
    public abstract List<AssignTagCommand> tags();

    /**
     * Custom Fields
     */
    public abstract List<UpsertCustomFieldValueCommand> customFields();

    /**
     * Workspace ID
     */
    public abstract UpdateLoRaPropertiesCommand loraProperties();

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
    public abstract HashMap<String, Object> properties();

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
