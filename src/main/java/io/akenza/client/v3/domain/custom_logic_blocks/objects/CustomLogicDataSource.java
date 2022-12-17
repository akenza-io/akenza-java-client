package io.akenza.client.v3.domain.custom_logic_blocks.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomLogicDataSource.class)
@JsonDeserialize(as = ImmutableCustomLogicDataSource.class)
@AkenzaStyle
public interface CustomLogicDataSource {
    String name();

    @Nullable
    String description();

    String variableName();

    @Nullable
    String defaultTopic();

    @Nullable
    String defaultDataKey();

    @Nullable
    Boolean lastSample();

    @Nullable
    Boolean triggerOnUplink();
}
