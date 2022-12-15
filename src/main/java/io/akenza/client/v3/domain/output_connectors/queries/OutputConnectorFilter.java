package io.akenza.client.v3.domain.output_connectors.queries;

import io.akenza.client.utils.BaseFilter;
import io.akenza.client.v3.domain.output_connectors.objects.OutputConnectorScope;
import io.akenza.client.v3.domain.output_connectors.objects.OutputConnectorType;

public class OutputConnectorFilter extends BaseFilter<OutputConnectorFilter> {
    public static OutputConnectorFilter create() {
        return new OutputConnectorFilter();
    }

    public OutputConnectorFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public OutputConnectorFilter withScope(OutputConnectorScope scope) {
        parameters.put("scope", scope);
        return this;
    }

    public OutputConnectorFilter withScope(OutputConnectorType type) {
        parameters.put("type", type);
        return this;
    }

    public OutputConnectorFilter onlyGlobal() {
        parameters.put("global", true);
        return this;
    }

    @Override
    protected OutputConnectorFilter getThis() {
        return this;
    }
}