package io.akenza.client.v3.domain.tags;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableTagPage.class)
@JsonDeserialize(as = ImmutableTagPage.class)
@AkenzaStyle
public abstract class TagPage implements Page<Tag> {
    public abstract List<Tag> content();
}
