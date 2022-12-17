package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableRuleLogic.class)
@JsonDeserialize(as = ImmutableRuleLogic.class)
@AkenzaStyle
public interface RuleLogic {
    LogicType type();

    List<LogicSource> dataSources();

    @Nullable
    List<ComparisonLogic> logicBlocks();

    @Nullable
    GeoFenceLogic geoFenceLogicBlock();

    @Nullable
    CustomLogic customLogic();
}
