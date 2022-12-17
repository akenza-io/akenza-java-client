package io.akenza.client.v3.domain.custom_fields.queries;

import io.akenza.client.utils.BaseFilter;

public class CustomFieldFilter extends BaseFilter<CustomFieldFilter> {
    public static CustomFieldFilter create() {
        return new CustomFieldFilter();
    }

    public CustomFieldFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public CustomFieldFilter withType(String type) {
        parameters.put("type", type);
        return this;
    }

    @Override
    protected CustomFieldFilter getThis() {
        return this;
    }
}