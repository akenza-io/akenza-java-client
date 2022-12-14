package io.akenza.client.v3.domain.output_connectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOutputConnector.class)
@JsonDeserialize(as = ImmutableOutputConnector.class)
@AkenzaStyle
public interface OutputConnector {
}
