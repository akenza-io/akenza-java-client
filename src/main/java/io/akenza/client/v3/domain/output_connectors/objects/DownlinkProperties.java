package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.common.Connectivity;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

@Value.Immutable
@JsonSerialize(as = ImmutableDownlinkProperties.class)
@JsonDeserialize(as = ImmutableDownlinkProperties.class)
@AkenzaStyle
public abstract class DownlinkProperties {

    public abstract Set<String> targetDeviceIds();

    public abstract Connectivity downlinkType();

    @Nullable
    public abstract Integer port();

    @Nullable
    public abstract Boolean confirmed();

    @Nullable
    public abstract String topic();

    public abstract String dataFlowId();

    @Nullable
    public abstract Boolean raw();

    @Nullable
    public abstract String rawDownlinkPayload();

    @Nullable
    public abstract Map<String, Object> downlinkPayload();
}
