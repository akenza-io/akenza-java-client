package io.akenza.client.v3.domain.workspaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.v3.domain.workspaces.objects.WorkspaceSettings;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableWorkspace.class)
@JsonDeserialize(as = ImmutableWorkspace.class)
@AkenzaStyle
public interface Workspace extends Audited, Versioned {

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
     * Organization Id
     */
    String organizationId();

    /**
     * Workspace Settings
     */
    WorkspaceSettings settings();

    /**
     * Additional Properties
     */
    @Value.Default

    default HashMap<String, Object> properties() {
        return new HashMap<>();
    }
}
