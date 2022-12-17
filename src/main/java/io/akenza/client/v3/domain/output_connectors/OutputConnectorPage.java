package io.akenza.client.v3.domain.output_connectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableOutputConnectorPage.class)
@JsonDeserialize(as = ImmutableOutputConnectorPage.class)
@AkenzaStyle
public abstract class OutputConnectorPage implements Page<OutputConnector> {
    public abstract List<OutputConnector> content();
}
