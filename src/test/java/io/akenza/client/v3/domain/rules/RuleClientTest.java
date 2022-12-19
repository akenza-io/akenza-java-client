package io.akenza.client.v3.domain.rules;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.output_connectors.objects.OutputConnectorType;
import io.akenza.client.v3.domain.rules.commands.ImmutableCreateRuleCommand;
import io.akenza.client.v3.domain.rules.commands.ImmutableUpdateRuleCommand;
import io.akenza.client.v3.domain.rules.objects.*;
import io.akenza.client.v3.domain.rules.queries.RuleFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RuleClientTest {
    private AkenzaAPI client;
    private MockWebServer server;

    @BeforeEach
    public void setup() {
        server = new MockWebServer();
        client = AkenzaAPI.create(String.format("http://localhost:%s", server.getPort()), "apiKey");
    }

    @AfterEach
    public void teardown() throws IOException {
        server.shutdown();
    }

    @Test
    void getById() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("rules/comparison-rule.json"))
        );
        //act
        Rule rule = client.rules().getById("someDeviceConnectorId").execute();

        //assert
        assertThat(rule).isNotNull();
        assertThat(rule.id()).isEqualTo("2100000000000000");
        assertThat(rule.name()).isEqualTo("CO2 Alert");
        assertThat(rule.description()).isEmpty();
        assertThat(rule.version()).isZero();
        assertThat(rule.logic()).isNotNull();
        assertThat(rule.logic().type()).isEqualTo(LogicType.COMPARISON);
        assertThat(rule.logic().logicBlocks()).hasSize(1);
        assertThat(rule.active()).isTrue();
        assertThat(rule.actions()).hasSize(1);
        assertThat(rule.workspaceId()).isEqualTo("2900000000000000");
        assertThat(rule.updated()).isEqualTo("2022-05-25T17:42:30Z");
        assertThat(rule.created()).isEqualTo("2022-05-25T17:42:30Z");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("rules/rule-list.json"))
        );
        //act
        RulePage page = client.rules().list("someWorkspaceId", RuleFilter.create()).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(3);
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("rules/comparison-rule.json"))
        );
        //act
        Rule rule = client.rules().create(ImmutableCreateRuleCommand.builder()
                        .name("CO2 Alert")
                        .workspaceId("2900000000000000")
                        .active(true)
                        .addInputs(ImmutableRuleInput.builder()
                                .type(InputType.ASSET_TAG)
                                .name("co2")
                                .id("7100000000000000").build())
                        .logic(ImmutableRuleLogic.builder()
                                .type(LogicType.COMPARISON)
                                .addDataSources(ImmutableLogicSource.builder()
                                        .dataKey("co2")
                                        .dataTopic("default")
                                        .valueType(ValueType.NUMERICAL)
                                        .triggerOnUplink(true)
                                        .lastSample(false)
                                        //needs to be a newly generated id
                                        .id("dataSource::L3LVHL5JM81HG1LJYHI").build())
                                .addLogicBlocks(ImmutableComparisonLogic.builder()
                                        .addConditions(ImmutableComparisonCondition.builder()
                                                .operandFirst(ImmutableComparisonOperand.builder()
                                                        .type(ComparisonOperandType.DATA_SOURCE)
                                                        .dataSourceId("dataSource::L3LVHL5JM81HG1LJYHI")
                                                        .dataKey("co2").build())
                                                .operator(ComparisonOperator.GT)
                                                .operandSecond(ImmutableComparisonOperand.builder()
                                                        .type(ComparisonOperandType.CONSTANT)
                                                        .value(800).build())
                                                .order(0)
                                                .build())
                                        .addActionIds("0900000000000000").build())
                                .build())
                        .addActions(ImmutableRuleAction.builder().name("CO2 Alert").id("0900000000000000").type(OutputConnectorType.SMS).build())
                        .timer(ImmutableTimerProperties.builder().enabled(false).build())
                        .build())
                .execute();

        //assert
        assertThat(rule).isNotNull();
        assertThat(rule.id()).isEqualTo("2100000000000000");
        assertThat(rule.name()).isEqualTo("CO2 Alert");
        assertThat(rule.description()).isEmpty();
        assertThat(rule.version()).isZero();
        assertThat(rule.logic()).isNotNull();
        assertThat(rule.logic().type()).isEqualTo(LogicType.COMPARISON);
        assertThat(rule.logic().logicBlocks()).hasSize(1);
        assertThat(rule.active()).isTrue();
        assertThat(rule.actions()).hasSize(1);
        assertThat(rule.workspaceId()).isEqualTo("2900000000000000");
        assertThat(rule.updated()).isEqualTo("2022-05-25T17:42:30Z");
        assertThat(rule.created()).isEqualTo("2022-05-25T17:42:30Z");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/rules");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("rules/comparison-rule.json"))
        );

        //act
        Rule rule = client.rules().update(ImmutableUpdateRuleCommand.builder()
                        .id("2100000000000000")
                        .name("CO2 Alert")
                        .active(true)
                        .addInputs(ImmutableRuleInput.builder()
                                .type(InputType.ASSET_TAG)
                                .name("co2")
                                .id("7100000000000000").build())
                        .logic(ImmutableRuleLogic.builder()
                                .type(LogicType.COMPARISON)
                                .addDataSources(ImmutableLogicSource.builder()
                                        .dataKey("co2")
                                        .dataTopic("default")
                                        .valueType(ValueType.NUMERICAL)
                                        .triggerOnUplink(true)
                                        .lastSample(false)
                                        //needs to be a newly generated id
                                        .id("dataSource::L3LVHL5JM81HG1LJYHI").build())
                                .addLogicBlocks(ImmutableComparisonLogic.builder()
                                        .addConditions(ImmutableComparisonCondition.builder()
                                                .operandFirst(ImmutableComparisonOperand.builder()
                                                        .type(ComparisonOperandType.DATA_SOURCE)
                                                        .dataSourceId("dataSource::L3LVHL5JM81HG1LJYHI")
                                                        .dataKey("co2").build())
                                                .operator(ComparisonOperator.GT)
                                                .operandSecond(ImmutableComparisonOperand.builder()
                                                        .type(ComparisonOperandType.CONSTANT)
                                                        .value(800).build())
                                                .order(0)
                                                .build())
                                        .addActionIds("0900000000000000").build())
                                .build())
                        .addActions(ImmutableRuleAction.builder().name("CO2 Alert").id("0900000000000000").type(OutputConnectorType.SMS).build())
                        .timer(ImmutableTimerProperties.builder().enabled(false).build())
                        .build())
                .execute();

        //assert
        AssertionsForClassTypes.assertThat(rule).isNotNull();
        AssertionsForClassTypes.assertThat(rule.id()).isEqualTo("2100000000000000");
        AssertionsForClassTypes.assertThat(rule.name()).isEqualTo("CO2 Alert");
        AssertionsForClassTypes.assertThat(rule.description()).isEmpty();
        AssertionsForClassTypes.assertThat(rule.version()).isZero();
        AssertionsForClassTypes.assertThat(rule.logic()).isNotNull();
        assertThat(rule.logic().type()).isEqualTo(LogicType.COMPARISON);
        assertThat(rule.logic().logicBlocks()).hasSize(1);
        AssertionsForClassTypes.assertThat(rule.active()).isTrue();
        assertThat(rule.actions()).hasSize(1);
        AssertionsForClassTypes.assertThat(rule.workspaceId()).isEqualTo("2900000000000000");
        AssertionsForClassTypes.assertThat(rule.updated()).isEqualTo("2022-05-25T17:42:30Z");
        AssertionsForClassTypes.assertThat(rule.created()).isEqualTo("2022-05-25T17:42:30Z");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/rules/2100000000000000");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.deviceConnectors().delete("0700000000000001").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-connectors/0700000000000001");
    }
}
