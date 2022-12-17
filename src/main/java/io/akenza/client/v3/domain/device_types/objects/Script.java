package io.akenza.client.v3.domain.device_types.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableScript.class)
@JsonDeserialize(as = ImmutableScript.class)
@AkenzaStyle
public interface Script {
    @Nullable
    Integer version();

    @Nullable
    String code();
}
