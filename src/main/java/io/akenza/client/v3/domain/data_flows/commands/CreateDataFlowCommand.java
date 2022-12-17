package io.akenza.client.v3.domain.data_flows.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.data_flows.objects.DeviceConnectorReference;
import io.akenza.client.v3.domain.data_flows.objects.DeviceTypeReference;
import io.akenza.client.v3.domain.data_flows.objects.OutputConnectorReference;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDataFlowCommand.class)
@JsonDeserialize(as = ImmutableCreateDataFlowCommand.class)
@AkenzaStyle
public abstract class CreateDataFlowCommand {
    /**
     * Name
     */
    public abstract String name();

    public abstract DeviceConnectorReference deviceConnector();

    @Nullable
    public abstract DeviceTypeReference deviceType();

    public abstract List<OutputConnectorReference> outputConnectors();

    @Value.Default
    public Boolean isPassThrough() {
        return true;
    }

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Workspace ID
     */
    @Nullable
    public abstract String workspaceId();
}
