package io.akenza.client.v3.domain.aggregations.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableInterval.class)
@JsonDeserialize(as = ImmutableInterval.class)
@Value.Style(
        jdkOnly = true, //do not use Guava
        visibility = Value.Style.ImplementationVisibility.PUBLIC, // Generated class will be always public,
        from = "fromInterval" //fixes an issue where from was overridden
)
public interface Interval {
    Instant from();

    Instant to();
}
