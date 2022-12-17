package io.akenza.client.v3.domain.organizations.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOwner.class)
@JsonDeserialize(as = ImmutableOwner.class)
@AkenzaStyle
public interface Owner {

    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

    /**
     * Email
     */
    String email();
}
