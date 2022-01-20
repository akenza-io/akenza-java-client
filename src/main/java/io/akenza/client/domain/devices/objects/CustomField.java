package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Audited;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomField.class)
@JsonDeserialize(as = ImmutableCustomField.class)
public interface CustomField extends Audited {
    CustomFieldMeta meta();

    @JsonProperty("STRING")
    String string();

    @JsonProperty("NUMBER")
    BigDecimal number();

    @JsonProperty("DATE")
    Date date();

    @JsonProperty("JSON")
    Map<String, Object> json();

    @JsonProperty("GPS_COORDINATES")
    GpsCoordinates gpsCoordinates();
}
