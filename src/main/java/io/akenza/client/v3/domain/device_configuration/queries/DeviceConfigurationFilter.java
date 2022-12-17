package io.akenza.client.v3.domain.device_configuration.queries;

import io.akenza.client.utils.BaseFilter;

public class DeviceConfigurationFilter extends BaseFilter<DeviceConfigurationFilter> {
    public static DeviceConfigurationFilter create() {
        return new DeviceConfigurationFilter();
    }

    @Override
    protected DeviceConfigurationFilter getThis() {
        return this;
    }
}