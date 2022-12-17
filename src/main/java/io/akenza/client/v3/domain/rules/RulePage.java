package io.akenza.client.v3.domain.rules;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableRulePage.class)
@JsonDeserialize(as = ImmutableRulePage.class)
@AkenzaStyle
public abstract class RulePage implements Page<Rule> {
    public abstract List<Rule> content();
}
