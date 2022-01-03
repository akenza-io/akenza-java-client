package io.akenza.client.domain.workspaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateWorkspaceCommand.class)
@JsonDeserialize(as = ImmutableUpdateWorkspaceCommand.class)
public interface UpdateWorkspaceCommand {
    /**
     * ID
     */
    String id();
    
    /**
     * Name
     */
    String name();

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
