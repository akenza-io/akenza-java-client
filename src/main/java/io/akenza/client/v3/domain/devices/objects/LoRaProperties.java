package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Preconditions;
import io.akenza.client.v3.domain.common.Carrier;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableLoRaProperties.class)
@JsonDeserialize(as = ImmutableLoRaProperties.class)
@AkenzaStyle
public abstract class LoRaProperties {
    /**
     * Carrier
     */
    public abstract Carrier carrier();

    /**
     * Activation Mode
     */
    public abstract ActivationMode activationMode();

    /**
     * Lora Version
     */
    public abstract String loraVersion();

    /**
     * Device Class
     */
    public abstract LoRaClass deviceClass();

    /**
     * Device EUI
     */
    public abstract String deviceEui();

    /**
     * Device Address
     */
    @Nullable
    public abstract String deviceAddress();

    /**
     * Application EUI
     */
    @Nullable
    public abstract String applicationEui();

    /**
     * Application Key
     */
    @Nullable
    public abstract String applicationKey();

    /**
     * Application Session Key
     */
    @Nullable
    public abstract String applicationSessionKey();

    /**
     * Network Session Key
     */
    @Nullable
    public abstract String networkSessionKey();

    /**
     * Join EUI
     */
    @Nullable
    public abstract String joinEui();

    /**
     * Forwarding Network Session Integrity Key
     */
    @Nullable
    public abstract String forwardingNetworkSessionIntegrityKey();

    /**
     * Network Session Encryption Key
     */
    @Nullable
    public abstract String networkSessionEncryptionKey();

    /**
     * Serving Network Session Integrity Key
     */
    @Nullable
    public abstract String servingNetworkSessionIntegrityKey();

    /**
     * Actility Properties
     */
    @Nullable
    public abstract ActilityProperties actilityProperties();

    /**
     * TTN Properties
     */
    @Nullable
    public abstract TtnProperties ttnProperties();

    @Value.Check
    protected void check() {
        if (carrier().equals(Carrier.TTN)) {
            Preconditions.checkState(ttnProperties() != null, "TTN properties must be set when creating TTN devices");
        } else if (carrier().equals(Carrier.SWISSCOM_ACTILITY) || carrier().equals(Carrier.GENERIC_ACTILITY)) {
            Preconditions.checkState(actilityProperties() != null, "Actility properties must be set when creating Actility devices");
        }
    }
}
