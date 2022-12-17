package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTtnProperties.class)
@JsonDeserialize(as = ImmutableTtnProperties.class)
@AkenzaStyle
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
