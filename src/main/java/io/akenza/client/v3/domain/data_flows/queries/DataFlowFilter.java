package io.akenza.client.v3.domain.data_flows.queries;

import io.akenza.client.utils.BaseFilter;

public class DataFlowFilter extends BaseFilter<DataFlowFilter> {
    public static DataFlowFilter create() {
        return new DataFlowFilter();
    }

    public DataFlowFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public DataFlowFilter withDetails() {
        parameters.put("details", true);
        return this;
    }

    @Override
    protected DataFlowFilter getThis() {
        return this;
    }
}