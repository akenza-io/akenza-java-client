package io.akenza.client.domain.workspaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateWorkspaceCommand.class)
@JsonDeserialize(as = ImmutableCreateWorkspaceCommand.class)
public interface CreateWorkspaceCommand {
    /**
     * Name
     */
    String name();

    /**
     * Organization ID
     */
    String organizationId();

    /**
     * Description
     */
    @Nullable
    String description();

    /**
     * Additional Properties
     */
    @Value.Default
    default HashMap<String, Object> properties() {
        return new HashMap<>();
    }
}
