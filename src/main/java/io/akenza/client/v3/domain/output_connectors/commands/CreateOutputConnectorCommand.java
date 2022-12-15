package io.akenza.client.v3.domain.output_connectors.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.output_connectors.objects.*;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateOutputConnectorCommand.class)
@JsonDeserialize(as = ImmutableCreateOutputConnectorCommand.class)
@AkenzaStyle
public abstract class CreateOutputConnectorCommand {
    /**
     * Name
     */
    public abstract String name();

    /**
     * The scope of the output connector
     */
    @Value.Default
    public OutputConnectorScope scope() {
        return OutputConnectorScope.DATA_FLOW;
    }

    /**
     * Workspace ID
     */
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


    public abstract Boolean useCustomPayload();

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

    @Value.Check
    protected void check() {
        Preconditions.checkState(Boolean.TRUE.equals(useCustomPayload()) && customPayload() == null, "Custom payload should only be set if useCustomPayload is true.");
    }
}
