package io.akenza.client.v3.domain.devices.queries;

import io.akenza.client.v3.domain.assets.queries.AssetFilter;
import org.jetbrains.annotations.NotNull;

public class DeviceFilter extends AssetFilter<DeviceFilter> {
    public static final String LORA_PROPERTIES = "loraProperties";
    public static final String ACTILITY_PROPERTIES = "actilityProperties";

    public static DeviceFilter create() {
        DeviceFilter deviceFilter = new DeviceFilter();
        deviceFilter.parameters.put("type", "DEVICE");
        return deviceFilter;
    }

    public DeviceFilter withOnline(Boolean online) {
        parameters.put("online", online);
        return this;
    }

    public DeviceFilter withProvider(String provider) {
        parameters.put("provider", provider);
        return this;
    }

    public DeviceFilter withDeviceClass(String deviceClass) {
        return addLoraProperty("deviceClass", deviceClass);
    }

    public DeviceFilter withActivationMode(String activationMode) {
        return addLoraProperty("activationMode", activationMode);
    }

    public DeviceFilter withCarrier(String carrier) {
        return addLoraProperty("carrier", carrier);
    }

    public DeviceFilter withDeviceProfileId(String deviceProfileId) {
        return addActilityProperty("deviceProfileId", deviceProfileId);
    }

    public DeviceFilter withConnectivityPlanId(String connectivityPlanId) {
        return addActilityProperty("connectivityPlanId", connectivityPlanId);
    }

    public DeviceFilter withRoutingProfileId(String routingProfileId) {
        return addActilityProperty("routingProfileId", routingProfileId);
    }

    public DeviceFilter withMotionIndicator(String motionIndicator) {
        return addActilityProperty("motionIndicator", motionIndicator);
    }

    @NotNull
    private DeviceFilter addLoraProperty(String propertyName, Object propertyValue) {
        parameters.put(LORA_PROPERTIES.concat(".").concat(propertyName), propertyValue);
        return this;
    }

    @NotNull
    private DeviceFilter addActilityProperty(String propertyName, Object propertyValue) {
        parameters.put(LORA_PROPERTIES.concat(".")
                .concat(ACTILITY_PROPERTIES).concat(".")
                .concat(propertyName), propertyValue);
        return this;
    }

    @Override
    protected DeviceFilter getThis() {
        return this;
    }
}
