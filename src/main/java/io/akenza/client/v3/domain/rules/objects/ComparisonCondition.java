package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableComparisonCondition.class)
@JsonDeserialize(as = ImmutableComparisonCondition.class)
@AkenzaStyle
public interface ComparisonCondition {
    ComparisonOperand operandFirst();

    ComparisonOperator operator();

    ComparisonOperand operandSecond();

    Integer order();

    /**
     * chain operator for the next comparison condition
     */
    @Nullable
    ComparisonChainOperator chainOperator();
}
