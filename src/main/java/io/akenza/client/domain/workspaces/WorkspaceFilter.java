package io.akenza.client.domain.workspaces;

import io.akenza.client.utils.BaseFilter;
import io.akenza.client.utils.SortDirection;

public class WorkspaceFilter extends BaseFilter {
    public WorkspaceFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public WorkspaceFilter withName(String name) {
        parameters.put("name", name);
        return this;
    }

    public WorkspaceFilter withPageSize(Integer size) {
        parameters.put("size", size);
        return this;
    }

    public WorkspaceFilter withPageNumber(Integer page) {
        parameters.put("page", page);
        return this;
    }

    public WorkspaceFilter withSort(SortDirection direction, String property) {
        String sort = String.format("%s,%s", direction.getValue(), property);
        parameters.put("sort", sort);
        return this;
    }
}
