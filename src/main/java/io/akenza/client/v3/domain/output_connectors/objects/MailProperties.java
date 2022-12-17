package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@JsonSerialize(as = ImmutableMailProperties.class)
@JsonDeserialize(as = ImmutableMailProperties.class)
@AkenzaStyle
public abstract class MailProperties {
    public abstract String messageTemplate();

    public abstract String subjectTemplate();

    public abstract Set<String> recipients();
}
