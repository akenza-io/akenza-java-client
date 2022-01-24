package io.akenza.client.domain.devices.commands.create;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.devices.objects.TtnProperties;
import io.akenza.client.domain.devices.objects.enums.ActivationMode;
import io.akenza.client.domain.devices.objects.enums.Carrier;
import io.akenza.client.domain.devices.objects.enums.LoRaClass;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateLoRaPropertiesCommand.class)
@JsonDeserialize(as = ImmutableCreateLoRaPropertiesCommand.class)
public interface CreateLoRaPropertiesCommand {
    /**
     * Carrier
     */
    Carrier carrier();

    /**
     * Activation Mode
     */
    ActivationMode activationMode();

    /**
     * Lora Version
     */
    String loraVersion();

    /**
     * Device Class
     */
    LoRaClass deviceClass();

    /**
     * Device EUI
     */
    String deviceEui();

    /**
     * Device Address
     */
    String deviceAddress();

    /**
     * Application EUI
     */
    String applicationEui();

    /**
     * Application Key
     */
    String applicationKey();

    /**
     * Application Session Key
     */
    String applicationSessionKey();

    /**
     * Network Session Key
     */
    String networkSessionKey();

    /**
     * Join EUI
     */
    String joinEui();

    /**
     * Forwarding Network Session Integrity Key
     */
    String forwardingNetworkSessionIntegrityKey();

    /**
     * Network Session Encryption Key
     */
    String networkSessionEncryptionKey();

    /**
     * Serving Network Session Integrity Key
     */
    String servingNetworkSessionIntegrityKey();

    /**
     * Actility Properties
     */
    CreateActilityPropertiesCommand actilityProperties();

    /**
     * TTN Properties
     */
    TtnProperties ttnProperties();
}
