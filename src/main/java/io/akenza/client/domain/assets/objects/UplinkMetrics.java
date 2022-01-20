package io.akenza.client.domain.assets.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableUplinkMetrics.class)
@JsonDeserialize(as = ImmutableUplinkMetrics.class)
public interface UplinkMetrics {
    String uplinkId();

    String deviceId();

    Integer uplinkSize();

    Instant timestamp();

    Double latitude();

    Double longitude();

    Integer port();

    Integer frameCountUp();

    Integer frameCountDown();

    Double rssi();

    Double snr();

    Integer sf();

    Double txPower();

    Integer numberOfGateways();

    Double esp();

    Integer sqi();

    Double batteryLevel();
}
