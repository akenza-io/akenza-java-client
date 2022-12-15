package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableInfluxProperties.class)
@JsonDeserialize(as = ImmutableInfluxProperties.class)
@AkenzaStyle
public abstract class InfluxProperties {
    public abstract String uri();

    public abstract String token();

    public abstract String orgId();

    public abstract String bucket();

    public abstract String measurementName();
}
