package io.akenza.client.v3.domain.data_flows.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOutputConnectorReference.class)
@JsonDeserialize(as = ImmutableOutputConnectorReference.class)
@AkenzaStyle
public abstract class OutputConnectorReference {
    public static final String AKENZA_DB_ID = "0900000000000000";

    /**
     * ID
     */
    public abstract String id();

    /**
     * Topic Filter
     */
    @Value.Default
    public String topic() {
        return "*";
    }

    public static OutputConnectorReference akenzaDatabase() {
        return ImmutableOutputConnectorReference.builder().id(AKENZA_DB_ID).topic("*").build();
    }
}
