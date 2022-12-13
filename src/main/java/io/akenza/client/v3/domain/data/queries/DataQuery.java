package io.akenza.client.v3.domain.data.queries;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.data.objects.Timestamp;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableDataQuery.class)
@JsonDeserialize(as = ImmutableDataQuery.class)
@AkenzaStyle
public interface DataQuery {
    String DEFAULT_TOPIC = "default";
    int DEFAULT_LIMIT = 8000;

    @Nullable
    Timestamp timestamp();

    @Value.Default
    default String topic() {
        return DEFAULT_TOPIC;
    }

    @Value.Default
    default Integer limit() {
        return DEFAULT_LIMIT;
    }

    @Value.Default
    default Integer skip() {
        return 0;
    }
}
