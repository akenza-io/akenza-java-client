package io.akenza.client.v3.domain.aggregations.queries;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.aggregations.objects.AccumulatorType;
import io.akenza.client.v3.domain.aggregations.objects.Interval;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableAggregationQuery.class)
@JsonDeserialize(as = ImmutableAggregationQuery.class)
@AkenzaStyle
public abstract class AggregationQuery {
    @Value.Default
    public String topic() {
        return "default";
    }

    public abstract String dataKey();

    public abstract AccumulatorType accumulator();

    public abstract Interval interval();

    @Value.Default
    public String bucketInterval() {
        return "PT1H";
    }
}
