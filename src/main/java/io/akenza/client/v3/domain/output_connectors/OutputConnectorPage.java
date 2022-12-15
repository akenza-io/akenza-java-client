package io.akenza.client.v3.domain.output_connectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOutputConnectorPage.class)
@JsonDeserialize(as = ImmutableOutputConnectorPage.class)
@AkenzaStyle
public interface OutputConnectorPage extends Page<OutputConnector> {
}
