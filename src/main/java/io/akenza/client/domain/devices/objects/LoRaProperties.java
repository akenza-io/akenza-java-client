package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.devices.objects.enums.ActivationMode;
import io.akenza.client.domain.devices.objects.enums.Carrier;
import io.akenza.client.domain.devices.objects.enums.LoRaClass;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableLoRaProperties.class)
@JsonDeserialize(as = ImmutableLoRaProperties.class)
public interface LoRaProperties {
    Carrier carrier();

    ActivationMode activationMode();

    String loraVersion();

    LoRaClass deviceClass();

    String deviceEui();

    @Nullable
    String deviceAddress();

    @Nullable
    String applicationEui();

    @Nullable
    String joinEui();

    @Nullable
    String applicationKey();

    @Nullable
    String applicationSessionKey();

    @Nullable
    String networkSessionKey();

    @Nullable
    String forwardingNetworkSessionIntegrityKey();

    @Nullable
    String networkSessionEncryptionKey();

    @Nullable
    String servingNetworkSessionIntegrityKey();

    @Nullable
    ActilityPropertiesProperties actilityProperties();

    @Nullable
    TtnProperties ttnProperties();
}
