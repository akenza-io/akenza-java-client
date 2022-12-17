package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;


@Value.Immutable
@JsonSerialize(as = ImmutableRuleInput.class)
@JsonDeserialize(as = ImmutableRuleInput.class)
@AkenzaStyle
public interface RuleInput {
    @Nullable
    String id();

    String name();

    InputType type();

    @Nullable
    String tagCombination();
}
