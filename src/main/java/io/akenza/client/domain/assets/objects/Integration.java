package io.akenza.client.domain.assets.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.assets.objects.enums.IntegrationType;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableIntegration.class)
@JsonDeserialize(as = ImmutableIntegration.class)
public interface Integration {
    String id();

    String name();

    IntegrationType type();

    Boolean global();
}
