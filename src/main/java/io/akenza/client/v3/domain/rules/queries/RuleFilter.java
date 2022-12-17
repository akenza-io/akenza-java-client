package io.akenza.client.v3.domain.rules.queries;

import io.akenza.client.utils.BaseFilter;

import java.util.List;

public class RuleFilter extends BaseFilter<RuleFilter> {
    public static RuleFilter create() {
        return new RuleFilter();
    }

    public RuleFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public RuleFilter withCustomLogicBlockId(String customLogicBlockId) {
        parameters.put("customLogicBlockId", customLogicBlockId);
        return this;
    }

    public RuleFilter withInputIds(List<String> inputIds) {
        parameters.put("customLogicBlockId", inputIds);
        return this;
    }

    @Override
    protected RuleFilter getThis() {
        return this;
    }
}