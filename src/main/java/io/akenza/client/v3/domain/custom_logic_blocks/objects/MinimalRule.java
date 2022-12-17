package io.akenza.client.v3.domain.custom_logic_blocks.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalRule.class)
@JsonDeserialize(as = ImmutableMinimalRule.class)
@AkenzaStyle
public interface MinimalRule {
    String workspaceId();

    String name();

    Boolean active();

    String id();
}
