package io.akenza.client.v3.domain.data_flows.commands;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.data_flows.objects.DeviceTypeReference;
import io.akenza.client.v3.domain.data_flows.objects.OutputConnectorReference;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateDataFlowCommand.class)
@JsonDeserialize(as = ImmutableUpdateDataFlowCommand.class)
@AkenzaStyle
public abstract class UpdateDataFlowCommand {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

    @Nullable
    public abstract DeviceTypeReference deviceType();

    public abstract List<OutputConnectorReference> outputConnectors();

    @Value.Default
    @JsonAlias("passThrough")
    public Boolean isPassThrough() {
        return true;
    }

    /**
     * Description
     */
    @Nullable
    public abstract String description();
}
