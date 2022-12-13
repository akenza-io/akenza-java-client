package io.akenza.client.v3.domain.workspaces.queries;

import io.akenza.client.utils.BaseFilter;

public class WorkspaceFilter extends BaseFilter<WorkspaceFilter> {
    public static WorkspaceFilter create() {
        return new WorkspaceFilter();
    }

    public WorkspaceFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public WorkspaceFilter withName(String name) {
        parameters.put("name", name);
        return this;
    }

    @Override
    protected WorkspaceFilter getThis() {
        return this;
    }
}
