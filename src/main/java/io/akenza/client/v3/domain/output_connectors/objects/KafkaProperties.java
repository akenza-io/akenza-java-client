package io.akenza.client.v3.domain.output_connectors.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableKafkaProperties.class)
@JsonDeserialize(as = ImmutableKafkaProperties.class)
@AkenzaStyle
public abstract class KafkaProperties {

    public abstract String kafkaTopic();

    public abstract String kafkaBootstrapServers();

    @Value.Default
    public KafkaSecurityProtocol securityProtocol() {
        return KafkaSecurityProtocol.SASL_SSL;
    }

    @Value.Default
    public KafkaSaslMechanism saslMechanism() {
        return KafkaSaslMechanism.PLAIN;
    }

    public abstract String saslJaasUsername();

    public abstract String saslJaasPassword();
}
