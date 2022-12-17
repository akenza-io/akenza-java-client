package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableComparisonLogic.class)
@JsonDeserialize(as = ImmutableComparisonLogic.class)
@AkenzaStyle
public interface ComparisonLogic {
    List<ComparisonCondition> conditions();

    /**
     * the actions to trigger if the conditions match
     */
    List<String> actionIds();
}
