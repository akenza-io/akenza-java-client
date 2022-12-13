package io.akenza.client.v3.domain.workspaces.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.workspaces.objects.ImmutableWorkspaceSettings;
import io.akenza.client.v3.domain.workspaces.objects.WorkspaceSettings;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateWorkspaceCommand.class)
@JsonDeserialize(as = ImmutableCreateWorkspaceCommand.class)
@AkenzaStyle
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
     * Workspace Settings
     */
    @Value.Default
    default WorkspaceSettings settings() {
        return ImmutableWorkspaceSettings.builder().build();
    }

    /**
     * Additional Properties
     */
    @Value.Default
    default HashMap<String, Object> properties() {
        return new HashMap<>();
    }
}
