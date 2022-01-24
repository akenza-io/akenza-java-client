package io.akenza.client.domain.organizations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOrganizationPage.class)
@JsonDeserialize(as = ImmutableOrganizationPage.class)
public interface OrganizationPage extends Page<Organization> {
}
