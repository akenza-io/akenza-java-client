package io.akenza.client.v3.domain.rules;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.rules.objects.RuleAction;
import io.akenza.client.v3.domain.rules.objects.RuleInput;
import io.akenza.client.v3.domain.rules.objects.RuleLogic;
import io.akenza.client.v3.domain.rules.objects.TimerProperties;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableRule.class)
@JsonDeserialize(as = ImmutableRule.class)
@AkenzaStyle
public interface Rule extends Versioned, Audited {
    /**
     * ID
     */
    String id();

    /**
     * Name
     */
    String name();

    /**
     * Description
     */
    @Nullable
    String description();

    /**
     * Workspace ID
     */
    String workspaceId();

    List<RuleInput> inputs();

    RuleLogic logic();

    /**
     * reference to output connectors with SCOPE RULE_ENGINE
     */
    List<RuleAction> actions();

    @Nullable
    TimerProperties timer();

    /**
     * the next execution for timed rules
     */
    @Nullable
    Instant nextExecution();

    /**
     * whether the rule is active
     */
    Boolean active();
}
