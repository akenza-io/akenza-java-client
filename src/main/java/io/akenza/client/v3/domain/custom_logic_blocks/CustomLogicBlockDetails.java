package io.akenza.client.v3.domain.custom_logic_blocks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.CustomLogicDataSource;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.CustomLogicProperty;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.MinimalRule;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomLogicBlockDetails.class)
@JsonDeserialize(as = ImmutableCustomLogicBlockDetails.class)
@AkenzaStyle
public interface CustomLogicBlockDetails {
    String id();

    String organizationId();

    String name();

    @Nullable
    String description();

    List<CustomLogicDataSource> dataSources();

    List<CustomLogicProperty> properties();

    String script();

    /**
     * the rules in which this logic block is used
     */
    List<MinimalRule> rules();
}
