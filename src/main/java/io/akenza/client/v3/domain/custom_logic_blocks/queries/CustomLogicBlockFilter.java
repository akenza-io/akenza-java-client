package io.akenza.client.v3.domain.custom_logic_blocks.queries;

import io.akenza.client.utils.BaseFilter;

public class CustomLogicBlockFilter extends BaseFilter<CustomLogicBlockFilter> {
    public static CustomLogicBlockFilter create() {
        return new CustomLogicBlockFilter();
    }

    public CustomLogicBlockFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    @Override
    protected CustomLogicBlockFilter getThis() {
        return this;
    }
}