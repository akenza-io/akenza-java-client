package io.akenza.client.v3.domain.custom_logic_blocks;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableCustomLogicBlockPage.class)
@JsonDeserialize(as = ImmutableCustomLogicBlockPage.class)
@AkenzaStyle
public abstract class CustomLogicBlockPage implements Page<CustomLogicBlock> {
    public abstract List<CustomLogicBlock> content();
}
