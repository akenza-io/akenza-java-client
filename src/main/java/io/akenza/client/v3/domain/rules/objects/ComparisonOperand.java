package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableComparisonOperand.class)
@JsonDeserialize(as = ImmutableComparisonOperand.class)
@AkenzaStyle
public interface ComparisonOperand {
    ComparisonOperandType type();

    @Nullable
    ValueType valueType();

    /**
     * Data source operand fields
     */
    @Nullable
    String dataKey();

    @Nullable
    String dataSourceId();

    /**
     * Constant operand fields
     */
    @Nullable
    Object value();
}
