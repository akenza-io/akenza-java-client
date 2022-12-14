package io.akenza.client.v3.domain.device_connectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.common.Carrier;
import io.akenza.client.v3.domain.common.Connectivity;
import io.akenza.client.v3.domain.device_connectors.objects.AuthType;
import io.akenza.client.v3.domain.device_types.ImmutableDeviceType;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceType.class)
@JsonDeserialize(as = ImmutableDeviceType.class)
@AkenzaStyle
public interface DeviceConnector extends Versioned, Audited {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

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

    /**
     * Whether the device connector belongs to a tenant integration.
     */
    Boolean global();

    /**
     * Auth type of the device connector (BASIC or DEVICE_CREDENTIALS)
     */
    AuthType authType();

    /**
     * Uplink secret (only set for device connectors using AuthType.BASIC)
     */
    @Nullable
    String uplinkSecret();

    /**
     * Connectivity of the connector
     */
    Connectivity connectivity();

    /**
     * LoRa carrier
     */
    @Nullable
    Carrier carrier();

    /**
     * LoRa network provider
     */
    @Nullable
    String provider();

    /**
     * Integration backing the device connector (only used for LoRa connectors)
     */
    @Nullable
    String integrationId();


    /**
     * Yanzi properties (only set for Yanzi connectors)
     */
    @Nullable
    Object yanziProperties();

    /**
     * Whether the integration was deleted.
     */
    @Nullable
    Boolean integrationDeleted();

    /**
     * Whether this device connector uses a special decoding logic.
     */
    @Nullable
    String decodingType();
}
