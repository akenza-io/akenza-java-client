package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTtnProperties.class)
@JsonDeserialize(as = ImmutableTtnProperties.class)
public interface TtnProperties {
    String deviceId();

    String phyLoraVersion();

    String networkServer();

    String networkServerLabel();

    String applicationServer();

    String applicationServerId();

    String applicationServerLabel();

    String joinServer();

    String networkId();

    String frequencyPlan();

    boolean multicast();

    boolean support32BitFrameCount();

    boolean disablePayloadEncryption();
}
