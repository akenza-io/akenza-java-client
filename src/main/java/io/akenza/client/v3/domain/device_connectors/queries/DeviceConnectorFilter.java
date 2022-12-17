package io.akenza.client.v3.domain.device_connectors.queries;

import io.akenza.client.utils.BaseFilter;

public class DeviceConnectorFilter extends BaseFilter<DeviceConnectorFilter> {
    public static DeviceConnectorFilter create() {
        return new DeviceConnectorFilter();
    }

    public DeviceConnectorFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    @Override
    protected DeviceConnectorFilter getThis() {
        return this;
    }
}