package io.akenza.client.domain.organizations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Audited;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableOrganization.class)
@JsonDeserialize(as = ImmutableOrganization.class)
public interface Organization extends Audited {

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
     * Logo
     */
    @Nullable
    String logo();

    /**
     * Owner
     */
    @Nullable
    OwnerInfo owner();
}
