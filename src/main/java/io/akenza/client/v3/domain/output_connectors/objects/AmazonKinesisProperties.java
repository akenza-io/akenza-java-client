package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableAmazonKinesisProperties.class)
@JsonDeserialize(as = ImmutableAmazonKinesisProperties.class)
@AkenzaStyle
public interface AmazonKinesisProperties {
    String iamAccessKeyId();

    String iamSecretAccessKey();

    String kinesisArn();
}
