package io.akenza.client.v3.domain.common;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMessageResponse.class)
@JsonDeserialize(as = ImmutableMessageResponse.class)
@AkenzaStyle
public interface MessageResponse {
    String message();
}
