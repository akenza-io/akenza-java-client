package io.akenza.client.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseFilter<T extends BaseFilter<T>> {
    protected final Map<String, Object> parameters = new HashMap<>();

    protected abstract T getThis();

    public Map<String, Object> getAsMap() {
        return parameters;
    }

    public T withPageSize(Integer size) {
        parameters.put("size", size);
        return getThis();
    }

    public T withPageNumber(Integer page) {
        parameters.put("page", page);
        return getThis();
    }

    public T withSort(SortDirection direction, String property) {
        String sort = String.format("%s,%s", direction.getValue(), property);
        parameters.put("sort", sort);
        return getThis();
    }
}
