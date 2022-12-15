package io.akenza.client.v3.domain.devices;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDevicePage.class)
@JsonDeserialize(as = ImmutableDevicePage.class)
public abstract class DevicePage implements Page<Device> {
    public abstract List<Device> content();
}
