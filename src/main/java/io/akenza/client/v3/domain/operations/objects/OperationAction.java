package io.akenza.client.v3.domain.operations.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableOperationAction.class)
@JsonDeserialize(as = ImmutableOperationAction.class)
@AkenzaStyle
public interface OperationAction {
    String id();

    ActionType type();

    ActionState state();

    Map<String, Object> data();

    Date started();

    @Nullable
    Date completed();
}
