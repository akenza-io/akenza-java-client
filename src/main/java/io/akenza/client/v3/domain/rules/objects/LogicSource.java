package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableLogicSource.class)
@JsonDeserialize(as = ImmutableLogicSource.class)
@AkenzaStyle
public interface LogicSource {
    String id();

    /**
     * reference to the rule input ids
     */
    List<String> inputIds();

    String dataTopic();

    String dataKey();

    @Nullable
    String variableName();

    ValueType valueType();

    /**
     * whether to access the last sample
     * <p>
     * if true and if the uplink is triggered by the input device
     * this means that instead of using the value from the uplink event
     * the previous value is used
     */
    Boolean lastSample();

    /**
     * whether to trigger the rule if the input device sends and uplink
     */
    boolean triggerOnUplink();
}
