package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableWebhookProperties.class)
@JsonDeserialize(as = ImmutableWebhookProperties.class)
@AkenzaStyle
public abstract class WebhookProperties {
    public abstract WebhookAuthType authType();

    public abstract String uri();

    @Value.Default
    public WebhookContentType contentType() {
        return WebhookContentType.JSON;
    }

    @Value.Default
    public WebhookHttpMethod method() {
        return WebhookHttpMethod.POST;
    }

    @Value.Default
    public Map<String, String> headers() {
        return new HashMap<>();
    }

    @Value.Default
    public Map<String, String> authHeaders() {
        return new HashMap<>();
    }

    @Nullable
    public abstract String basicCredentials();

    @Nullable
    public abstract String bearerToken();
}
