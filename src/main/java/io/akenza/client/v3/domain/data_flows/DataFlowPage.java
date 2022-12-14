package io.akenza.client.v3.domain.data_flows;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableDataFlowPage.class)
@JsonDeserialize(as = ImmutableDataFlowPage.class)
@AkenzaStyle
public interface DataFlowPage extends Page<DataFlow> {
}
