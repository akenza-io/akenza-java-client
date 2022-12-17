package io.akenza.client.v3.domain.tags.queries;

import io.akenza.client.utils.BaseFilter;

public class TagFilter extends BaseFilter<TagFilter> {
    public static TagFilter create() {
        return new TagFilter();
    }

    public TagFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    @Override
    protected TagFilter getThis() {
        return this;
    }
}