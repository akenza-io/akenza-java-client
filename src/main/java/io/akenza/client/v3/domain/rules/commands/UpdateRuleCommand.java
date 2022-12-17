package io.akenza.client.v3.domain.rules.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.rules.objects.RuleAction;
import io.akenza.client.v3.domain.rules.objects.RuleInput;
import io.akenza.client.v3.domain.rules.objects.RuleLogic;
import io.akenza.client.v3.domain.rules.objects.TimerProperties;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateRuleCommand.class)
@JsonDeserialize(as = ImmutableUpdateRuleCommand.class)
@AkenzaStyle
public abstract class UpdateRuleCommand {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    public abstract List<RuleInput> inputs();

    public abstract RuleLogic logic();

    /**
     * reference to output connectors with SCOPE RULE_ENGINE
     */
    public abstract List<RuleAction> actions();

    @Nullable
    public abstract TimerProperties timer();

    /**
     * whether the rule is active
     */
    public abstract Boolean active();
}
