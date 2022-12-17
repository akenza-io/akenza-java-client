package io.akenza.client.v3.domain.aggregations.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.AllowNulls;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableTimeSeriesAggregationResult.class)
@JsonDeserialize(as = ImmutableTimeSeriesAggregationResult.class)
@AkenzaStyle
public interface TimeSeriesAggregationResult {
    String dataKey();

    String topic();

    String deviceId();

    List<@AllowNulls Number> dataPoints();

    List<Instant> timestamps();
}
