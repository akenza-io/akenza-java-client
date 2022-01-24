package io.akenza.client.domain.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableDataQuery.class)
@JsonDeserialize(as = ImmutableDataQuery.class)
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
