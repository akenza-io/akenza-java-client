package io.akenza.client.domain.organizations;

import io.akenza.client.utils.BaseFilter;

public class OrganizationFilter extends BaseFilter<OrganizationFilter> {
    public static OrganizationFilter create() {
        return new OrganizationFilter();
    }

    public OrganizationFilter withName(String name) {
        parameters.put("name", name);
        return this;
    }

    @Override
    protected OrganizationFilter getThis() {
        return this;
    }
}
