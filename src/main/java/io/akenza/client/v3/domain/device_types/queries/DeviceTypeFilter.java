package io.akenza.client.v3.domain.device_types.queries;

import io.akenza.client.utils.BaseFilter;

public class DeviceTypeFilter extends BaseFilter<DeviceTypeFilter> {
    public static DeviceTypeFilter create() {
        return new DeviceTypeFilter();
    }

    public DeviceTypeFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public DeviceTypeFilter withType(String type) {
        parameters.put("types", type);
        return this;
    }

    public DeviceTypeFilter withDetails() {
        parameters.put("details", true);
        return this;
    }

    @Override
    protected DeviceTypeFilter getThis() {
        return this;
    }
}