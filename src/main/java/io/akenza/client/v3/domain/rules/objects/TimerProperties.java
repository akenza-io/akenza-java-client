package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableTimerProperties.class)
@JsonDeserialize(as = ImmutableTimerProperties.class)
@AkenzaStyle
public interface TimerProperties {
    Boolean enabled();

    /**
     * a cron expression with the timer schedule
     */
    @Nullable
    String schedule();

    @Nullable
    TimerType type();

    @Nullable
    Instant startAt();
}
