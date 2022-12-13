package io.akenza.client.v3.domain.assets.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableIntegration.class)
@JsonDeserialize(as = ImmutableIntegration.class)
@AkenzaStyle
public interface Integration {
    String id();

    String name();

    IntegrationType type();

    Boolean global();
}
