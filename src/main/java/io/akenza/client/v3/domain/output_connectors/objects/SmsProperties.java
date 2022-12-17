package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@JsonSerialize(as = ImmutableSmsProperties.class)
@JsonDeserialize(as = ImmutableSmsProperties.class)
@AkenzaStyle
public abstract class SmsProperties {
    public abstract String messageText();

    public abstract Set<SmsRecipient> recipientPhones();
}
