package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.output_connectors.objects.OutputConnectorType;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableRuleAction.class)
@JsonDeserialize(as = ImmutableRuleAction.class)
@AkenzaStyle
public interface RuleAction {
    /**
     * output connector id
     */
    String id();

    String name();

    OutputConnectorType type();

    Map<String, Object> configuration();
}
