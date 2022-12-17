package io.akenza.client.v3.domain.aggregations.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableKpiAggregationResult.class)
@JsonDeserialize(as = ImmutableKpiAggregationResult.class)
@AkenzaStyle
public interface KpiAggregationResult {
    String dataKey();

    String topic();

    String deviceId();

    @Nullable
    Number kpi();
}
