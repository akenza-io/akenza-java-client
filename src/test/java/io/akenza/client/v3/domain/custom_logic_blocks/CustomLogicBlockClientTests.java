package io.akenza.client.v3.domain.custom_logic_blocks;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.custom_logic_blocks.commands.ImmutableCreateCustomLogicBlockCommand;
import io.akenza.client.v3.domain.custom_logic_blocks.commands.ImmutableUpdateCustomLogicBlockCommand;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.ImmutableCustomLogicDataSource;
import io.akenza.client.v3.domain.custom_logic_blocks.objects.ImmutableCustomLogicProperty;
import io.akenza.client.v3.domain.custom_logic_blocks.queries.CustomLogicBlockFilter;
import io.akenza.client.v3.domain.rules.objects.ValueType;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CustomLogicBlockClientTests {
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
    void getById() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("custom_logic_blocks/custom-logic-block.json"))
        );

        //act
        CustomLogicBlockDetails customLogicBlock = client.customLogicBlocks().getById("someDeviceConnectorId").execute();

        //assert
        assertThat(customLogicBlock).isNotNull();
        assertThat(customLogicBlock.id()).isEqualTo("3700000000000000");
        assertThat(customLogicBlock.name()).isEqualTo("Smart Temperature Alert");
        assertThat(customLogicBlock.description()).isNull();
        assertThat(customLogicBlock.organizationId()).isEqualTo("2800000000000000");
        assertThat(customLogicBlock.dataSources()).hasSize(1);
        assertThat(customLogicBlock.properties()).hasSize(1);
        assertThat(customLogicBlock.script()).isNotBlank();
    }

    @Test
    void getAll() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("custom_logic_blocks/custom-logic-block-list.json"))
        );
        //act
        CustomLogicBlockPage page = client.customLogicBlocks().list("2800000000000000", CustomLogicBlockFilter.create()).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(2);
    }

    @Test
    void create() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("custom_logic_blocks/custom-logic-block.json"))
        );

        //act
        CustomLogicBlock customLogicBlock = client.customLogicBlocks()
                .create(ImmutableCreateCustomLogicBlockCommand.builder()
                        .name("Smart Temperature Alert")
                        .organizationId("2800000000000000")
                        .script("some script")
                        .addDataSources(ImmutableCustomLogicDataSource.builder()
                                .name("Temperature")
                                .variableName("temperature")
                                .defaultDataKey("temperature")
                                .defaultTopic("default")
                                .lastSample(false)
                                .triggerOnUplink(true)
                                .build())
                        .addProperties(ImmutableCustomLogicProperty.builder()
                                .name("Initial Threshold")
                                .variableName("initialThreshold")
                                .defaultValue(20)
                                .type(ValueType.NUMERICAL)
                                .build())
                        .build())
                .execute();

        //assert
        assertThat(customLogicBlock).isNotNull();
        assertThat(customLogicBlock.id()).isEqualTo("3700000000000000");
        assertThat(customLogicBlock.name()).isEqualTo("Smart Temperature Alert");
        assertThat(customLogicBlock.description()).isNull();
        assertThat(customLogicBlock.organizationId()).isEqualTo("2800000000000000");
        assertThat(customLogicBlock.dataSources()).hasSize(1);
        assertThat(customLogicBlock.properties()).hasSize(1);
        assertThat(customLogicBlock.script()).isNotBlank();

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/rules/custom");
    }

    @Test
    void update() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("custom_logic_blocks/custom-logic-block.json"))
        );

        //act
        CustomLogicBlock customLogicBlock = client.customLogicBlocks()
                .update(ImmutableUpdateCustomLogicBlockCommand.builder()
                        .id("3700000000000000")
                        .name("Smart Temperature Alert")
                        .script("some script")
                        .addDataSources(ImmutableCustomLogicDataSource.builder()
                                .name("Temperature")
                                .variableName("temperature")
                                .defaultDataKey("temperature")
                                .defaultTopic("default")
                                .lastSample(false)
                                .triggerOnUplink(true)
                                .build())
                        .addProperties(ImmutableCustomLogicProperty.builder()
                                .name("Initial Threshold")
                                .variableName("initialThreshold")
                                .defaultValue(20)
                                .type(ValueType.NUMERICAL)
                                .build())
                        .build())
                .execute();

        //assert
        assertThat(customLogicBlock).isNotNull();
        assertThat(customLogicBlock.id()).isEqualTo("3700000000000000");
        assertThat(customLogicBlock.name()).isEqualTo("Smart Temperature Alert");
        assertThat(customLogicBlock.description()).isNull();
        assertThat(customLogicBlock.organizationId()).isEqualTo("2800000000000000");
        assertThat(customLogicBlock.dataSources()).hasSize(1);
        assertThat(customLogicBlock.properties()).hasSize(1);
        assertThat(customLogicBlock.script()).isNotBlank();

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/rules/custom/3700000000000000");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.customLogicBlocks().delete("3700000000000000").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/rules/custom/3700000000000000");
    }
}
