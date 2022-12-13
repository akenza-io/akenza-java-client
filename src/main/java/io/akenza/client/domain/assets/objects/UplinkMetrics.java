package io.akenza.client.domain.assets.objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableUplinkMetrics.class)
@JsonDeserialize(as = ImmutableUplinkMetrics.class)
public interface UplinkMetrics {
    String uplinkId();

    @JsonAlias("deviceId")
    String akenzaDeviceId();

    Integer uplinkSize();

    Instant timestamp();

    @Nullable
    Double latitude();

    @Nullable
    Double longitude();

    @Nullable
    Integer port();

    @Nullable
    Integer frameCountUp();

    @Nullable
    Integer frameCountDown();

    @Nullable
    Double rssi();

    @Nullable
    Double snr();

    @Nullable
    Integer sf();

    @Nullable
    Double txPower();

    @Nullable
    Integer numberOfGateways();

    @Nullable
    Double esp();

    @Nullable
    Integer sqi();

    @Nullable
    Double batteryLevel();
}
