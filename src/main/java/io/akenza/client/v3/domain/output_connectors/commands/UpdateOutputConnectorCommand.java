package io.akenza.client.v3.domain.output_connectors.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.output_connectors.objects.*;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateOutputConnectorCommand.class)
@JsonDeserialize(as = ImmutableUpdateOutputConnectorCommand.class)
@AkenzaStyle
public abstract class UpdateOutputConnectorCommand {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

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
