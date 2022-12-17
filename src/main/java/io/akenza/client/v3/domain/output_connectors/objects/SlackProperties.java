package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSlackProperties.class)
@JsonDeserialize(as = ImmutableSlackProperties.class)
@AkenzaStyle
public abstract class SlackProperties {
    public abstract String message();

    public abstract String webhookUrl();
}
