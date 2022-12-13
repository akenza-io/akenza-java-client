package io.akenza.client.domain.organizations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateOrganizationCommand.class)
@JsonDeserialize(as = ImmutableCreateOrganizationCommand.class)
public interface CreateOrganizationCommand {
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
     * Logo
     */
    @Nullable
    String logo();

    /**
     * Additional Properties
     */
    @Value.Default
    default HashMap<String, Object> properties() {
        return new HashMap<>();
    }
}
