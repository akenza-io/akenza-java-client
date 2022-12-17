package io.akenza.client.v3.domain.data_flows;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.data_flows.objects.DeviceConnectorReference;
import io.akenza.client.v3.domain.data_flows.objects.MinimalDeviceType;
import io.akenza.client.v3.domain.data_flows.objects.OutputConnectorReference;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDataFlow.class)
@JsonDeserialize(as = ImmutableDataFlow.class)
@AkenzaStyle
public interface DataFlow extends Versioned, Audited {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

    DeviceConnectorReference deviceConnector();

    @Nullable
    MinimalDeviceType deviceType();

    List<OutputConnectorReference> outputConnectors();

    @JsonProperty("passThrough")
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
