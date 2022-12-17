package io.akenza.client.v3.domain.device_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableYanziProperties.class)
@JsonDeserialize(as = ImmutableYanziProperties.class)
@AkenzaStyle
public interface YanziProperties {
    String locationId();

    String username();

    String host();
}
