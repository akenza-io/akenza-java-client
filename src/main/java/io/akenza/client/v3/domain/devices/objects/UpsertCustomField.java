package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Preconditions;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableUpsertCustomField.class)
@JsonDeserialize(as = ImmutableUpsertCustomField.class)
@AkenzaStyle
public abstract class UpsertCustomField {
    public abstract String fieldMetaId();

    @JsonProperty("STRING")
    public abstract String string();

    @JsonProperty("NUMBER")
    public abstract BigDecimal number();

    @JsonProperty("DATE")
    public abstract Date date();

    @JsonProperty("JSON")
    public abstract Map<String, Object> json();

    @JsonProperty("GPS_COORDINATES")
    public abstract GpsCoordinates gpsCoordinates();

    @Value.Check
    protected void check() {
        Preconditions.checkState(!(fieldMetaId() == null && string() == null && number() == null && date() == null && json() == null && gpsCoordinates() == null)
                        || (fieldMetaId() != null && string() != null && number() != null && date() != null && json() != null && gpsCoordinates() != null),
                "Exactly one of the following fields have to be set: fieldMetaId, string, number, date, json, gpsCoordinates");
    }
}
