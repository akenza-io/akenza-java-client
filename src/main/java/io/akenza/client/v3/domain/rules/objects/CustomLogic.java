package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomLogic.class)
@JsonDeserialize(as = ImmutableCustomLogic.class)
@AkenzaStyle
public interface CustomLogic {
    /**
     * reference to the custom logic block
     */
    String customLogicId();

    /**
     * action references that should be triggered by the custom logic block
     */
    List<String> actionIds();

    /**
     * values for the properties of the custom logic block
     */
    List<CustomLogicProperty> properties();

    /**
     * the name of the custom logic block
     */
    String name();
}
