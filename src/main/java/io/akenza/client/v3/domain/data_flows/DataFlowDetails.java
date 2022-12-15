package io.akenza.client.v3.domain.data_flows;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.device_connectors.DeviceConnector;
import io.akenza.client.v3.domain.device_types.DeviceType;
import io.akenza.client.v3.domain.output_connectors.OutputConnector;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDataFlowDetails.class)
@JsonDeserialize(as = ImmutableDataFlowDetails.class)
@AkenzaStyle
public interface DataFlowDetails extends Versioned, Audited {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

    DeviceConnector deviceConnector();

    @Nullable
    DeviceType deviceType();

    List<OutputConnector> outputConnectors();

    @JsonAlias("passThrough")
    Boolean isPassThrough();

    /**
     * Description
     */
    @Nullable
    String description();

    /**
     * Workspace ID
     */
    @Nullable
    String workspaceId();
}
