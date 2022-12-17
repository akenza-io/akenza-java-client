package io.akenza.client.v3.domain.device_credentials;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceCredentialPage.class)
@JsonDeserialize(as = ImmutableDeviceCredentialPage.class)
@AkenzaStyle
public abstract class DeviceCredentialPage implements Page<DeviceCredential> {
    public abstract List<DeviceCredential> content();
}
