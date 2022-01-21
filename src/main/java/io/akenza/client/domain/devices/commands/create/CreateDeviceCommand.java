package io.akenza.client.domain.devices.commands.create;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import io.akenza.client.domain.devices.commands.AssignTagCommand;
import io.akenza.client.domain.devices.commands.UpsertCustomFieldValueCommand;
import io.akenza.client.domain.devices.objects.enums.Connectivity;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceCommand.class)
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
     * device ID
     */
    @Nullable
    public abstract String deviceId();

    /**
     * Tags
     */
    @Nullable
    public abstract List<AssignTagCommand> tags();

    /**
     * Custom Fields
     */
    @Nullable
    public abstract List<UpsertCustomFieldValueCommand> customFields();

    /**
     * Workspace ID
     */
    public abstract String workspaceId();

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
