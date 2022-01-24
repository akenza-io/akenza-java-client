package io.akenza.client.domain.organizations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOwnerInfo.class)
@JsonDeserialize(as = ImmutableOwnerInfo.class)
public interface OwnerInfo {
    String id();

    String name();

    String email();
}
