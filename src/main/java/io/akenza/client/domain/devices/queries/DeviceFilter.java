package io.akenza.client.domain.devices.queries;

import io.akenza.client.domain.assets.queries.AssetFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class DeviceFilter extends AssetFilter {
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
    @SuppressWarnings("unchecked")
    private DeviceFilter addLoraProperty(String propertyName, Object propertyValue) {
        Map<String, Object> loraProperties = new HashMap<>();
        if (parameters.containsKey(LORA_PROPERTIES)) {
            loraProperties = ((Map<String, Object>) parameters.get(LORA_PROPERTIES));
        } else {
            parameters.put(LORA_PROPERTIES, loraProperties);
        }
        loraProperties.put(propertyName, propertyValue);
        return this;
    }

    @NotNull
    @SuppressWarnings("unchecked")
    private DeviceFilter addActilityProperty(String propertyName, Object propertyValue) {
        Map<String, Object> loraProperties = new HashMap<>();
        Map<String, Object> actilityProperties = new HashMap<>();
        if (parameters.containsKey(LORA_PROPERTIES)) {
            loraProperties = ((Map<String, Object>) parameters.get(LORA_PROPERTIES));
        } else {
            parameters.put(LORA_PROPERTIES, loraProperties);
        }
        if (loraProperties.containsKey(ACTILITY_PROPERTIES)) {
            actilityProperties = ((Map<String, Object>) parameters.get(ACTILITY_PROPERTIES));
        } else {
            parameters.put(ACTILITY_PROPERTIES, actilityProperties);
        }
        actilityProperties.put(propertyName, propertyValue);
        return this;
    }
}
