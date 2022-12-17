package io.akenza.client.v3.domain.operations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.operations.objects.OperationAction;
import io.akenza.client.v3.domain.operations.objects.OperationType;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableOperation.class)
@JsonDeserialize(as = ImmutableOperation.class)
@AkenzaStyle
public interface Operation {
    /**
     * ID
     */
    String id();

    String workspaceId();

    String creator();

    OperationType type();

    List<OperationAction> actions();

    Instant started();

    @Nullable
    Instant completed();

    Integer totalActionsRequired();

    Integer actionsCompleted();

    @Nullable
    Boolean succeeded();
}
