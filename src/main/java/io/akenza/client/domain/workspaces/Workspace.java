package io.akenza.client.domain.workspaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableWorkspace.class)
@JsonDeserialize(as = ImmutableWorkspace.class)
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
     * Additional Properties
     */
    @Value.Default
    default HashMap<String, Object> properties() {
        return new HashMap<>();
    }
}
