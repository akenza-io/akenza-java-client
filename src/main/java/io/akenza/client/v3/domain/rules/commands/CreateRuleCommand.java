package io.akenza.client.v3.domain.rules.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.rules.objects.*;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateRuleCommand.class)
@JsonDeserialize(as = ImmutableCreateRuleCommand.class)
@AkenzaStyle
public abstract class CreateRuleCommand {
    /**
     * Name
     */
    public abstract String name();

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Workspace ID
     */
    public abstract String workspaceId();

    public abstract List<RuleInput> inputs();

    public abstract RuleLogic logic();

    /**
     * reference to output connectors with SCOPE RULE_ENGINE
     */
    public abstract List<RuleAction> actions();

    @Nullable
    @Value.Default
    public TimerProperties timer() {
        return ImmutableTimerProperties.builder().enabled(false).build();
    }

    /**
     * whether the rule is active
     */
    @Value.Default
    public Boolean active() {
        return true;
    }
}
