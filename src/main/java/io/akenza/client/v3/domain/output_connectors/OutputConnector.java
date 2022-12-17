package io.akenza.client.v3.domain.output_connectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import io.akenza.client.utils.Versioned;
import io.akenza.client.v3.domain.output_connectors.objects.*;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableOutputConnector.class)
@JsonDeserialize(as = ImmutableOutputConnector.class)
@AkenzaStyle
public abstract class OutputConnector implements Versioned, Audited {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

    /**
     * The scope of the output connector
     */
    public abstract OutputConnectorScope scope();

    /**
     * Workspace ID
     */
    @Nullable
    public abstract String workspaceId();

    /**
     * Whether to send consecutive alerts
     */
    @Nullable
    public abstract Boolean sendConsecutiveAlerts();

    /**
     * Whether to invoke the rule engine again (only supported
     */
    @Nullable
    public abstract Boolean invokeRuleEngine();

    /**
     * The output connector type
     */
    public abstract OutputConnectorType type();
    
    @Nullable
    public abstract Boolean useCustomPayload();

    @Nullable
    public abstract String customPayload();

    @Nullable
    public abstract CustomAkenzaDbProperties customAkenzaDbProperties();

    @Nullable
    public abstract WebhookProperties webhookProperties();

    @Nullable
    public abstract MailProperties mailProperties();

    @Nullable
    public abstract DownlinkProperties downlinkProperties();

    @Nullable
    public abstract AzureProperties azureProperties();

    @Nullable
    public abstract SmsProperties smsProperties();

    @Nullable
    public abstract GcpProperties gcpProperties();

    @Nullable
    public abstract InfluxProperties influxProperties();

    @Nullable
    public abstract AmazonKinesisProperties amazonKinesisProperties();

    @Nullable
    public abstract MsTeamsProperties msTeamsProperties();

    @Nullable
    public abstract KafkaProperties kafkaProperties();

    @Nullable
    public abstract SlackProperties slackProperties();
}
