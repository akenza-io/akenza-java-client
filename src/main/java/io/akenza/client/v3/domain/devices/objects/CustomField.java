package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomField.class)
@JsonDeserialize(as = ImmutableCustomField.class)
public interface CustomField {
    MinimalCustomFieldMeta meta();

    @JsonProperty("STRING")
    @Nullable
    String string();

    @JsonProperty("NUMBER")
    @Nullable
    BigDecimal number();

    @JsonProperty("DATE")
    @Nullable
    Date date();

    @JsonProperty("JSON")
    @Nullable
    Map<String, Object> json();

    @JsonProperty("GPS_COORDINATES")
    @Nullable
    GpsCoordinates gpsCoordinates();
}
