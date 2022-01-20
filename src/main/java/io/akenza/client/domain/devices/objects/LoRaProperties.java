package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.devices.objects.enums.ActivationMode;
import io.akenza.client.domain.devices.objects.enums.Carrier;
import io.akenza.client.domain.devices.objects.enums.LoRaClass;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableLoRaProperties.class)
@JsonDeserialize(as = ImmutableLoRaProperties.class)
public interface LoRaProperties {
    Carrier carrier();

    ActivationMode activationMode();

    String loraVersion();

    LoRaClass deviceClass();

    String deviceEui();

    String deviceAddress();

    String applicationEui();

    String joinEui();

    String applicationKey();

    String applicationSessionKey();

    String networkSessionKey();

    String forwardingNetworkSessionIntegrityKey();

    String networkSessionEncryptionKey();

    String servingNetworkSessionIntegrityKey();

    ActilityPropertiesProperties actilityProperties();

    TtnProperties ttnProperties();
}
