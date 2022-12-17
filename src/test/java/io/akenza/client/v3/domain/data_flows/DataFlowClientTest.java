package io.akenza.client.v3.domain.data_flows;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.data_flows.commands.ImmutableCreateDataFlowCommand;
import io.akenza.client.v3.domain.data_flows.commands.ImmutableUpdateDataFlowCommand;
import io.akenza.client.v3.domain.data_flows.objects.ImmutableDeviceConnectorReference;
import io.akenza.client.v3.domain.data_flows.objects.ImmutableOutputConnectorReference;
import io.akenza.client.v3.domain.data_flows.queries.DataFlowFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DataFlowClientTest {
    private AkenzaAPI client;
    private MockWebServer server;

    @BeforeEach
    public void setup() {
        server = new MockWebServer();
        client = new AkenzaAPI(String.format("http://localhost:%s", server.getPort()), "apiKey");
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
                .setBody(TestUtils.getFixture("data_flows/lora-integration-data-flow.json"))
        );
        //act
        DataFlowDetails dataFlow = client.dataFlows().getById("0800000000000000").execute();

        //assert
        assertThat(dataFlow).isNotNull();
        assertThat(dataFlow.id()).isEqualTo("0800000000000000");
        assertThat(dataFlow.name()).isEqualTo("LPN Ranos DB 2 DF");
        assertThat(dataFlow.workspaceId()).isEqualTo("2900000000000000");
        assertThat(dataFlow.updated()).isNull();
        assertThat(dataFlow.deviceType()).isNotNull();
        assertThat(dataFlow.deviceConnector()).isNotNull();
        assertThat(dataFlow.deviceType()).isNotNull();
        assertThat(dataFlow.outputConnectors()).hasSize(2);
    }

    @Test
    void getAll() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("data_flows/data-flow-list.json"))
        );
        //act
        DataFlowPage page = client.dataFlows().list("someWorkspaceId", DataFlowFilter.create().withPageNumber(2).withSearch("test")).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(6);

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/data-flows");
        assertThat(recordedRequest.getRequestUrl().url().getQuery()).isEqualTo("search=test&page=2&workspaceId=someWorkspaceId");
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("data_flows/http-data-flow.json"))
        );

        //act
        DataFlowDetails dataFlow = client.dataFlows().create(ImmutableCreateDataFlowCommand.builder()
                .name("HTTP Passthrough DF")
                .workspaceId("2900000000000000")
                .deviceConnector(ImmutableDeviceConnectorReference.builder().id("0700000000000000").build())
                .isPassThrough(true)
                .addOutputConnectors(ImmutableOutputConnectorReference.builder().topic("*").id("0900000000000000").build())
                .build()).execute();

        //assert
        assertThat(dataFlow).isNotNull();
        assertThat(dataFlow.id()).isEqualTo("0800000000000000");
        assertThat(dataFlow.name()).isEqualTo("HTTP Passthrough DF");
        assertThat(dataFlow.workspaceId()).isEqualTo("2900000000000000");
        assertThat(dataFlow.updated()).isNull();

        assertThat(dataFlow.deviceConnector()).isNotNull();
        assertThat(dataFlow.deviceType()).isNull();
        assertThat(dataFlow.isPassThrough()).isTrue();
        assertThat(dataFlow.outputConnectors()).hasSize(1);

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/data-flows");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("data_flows/http-data-flow.json"))
        );

        //act
        DataFlowDetails dataFlow = client.dataFlows().update(ImmutableUpdateDataFlowCommand.builder()
                .id("0800000000000000")
                .name("HTTP Passthrough DF")
                .isPassThrough(true)
                .addOutputConnectors(ImmutableOutputConnectorReference.builder().topic("*").id("0900000000000000").build())
                .build()).execute();

        //assert
        assertThat(dataFlow).isNotNull();
        assertThat(dataFlow.id()).isEqualTo("0800000000000000");
        assertThat(dataFlow.name()).isEqualTo("HTTP Passthrough DF");
        assertThat(dataFlow.workspaceId()).isEqualTo("2900000000000000");
        assertThat(dataFlow.updated()).isNull();

        assertThat(dataFlow.deviceConnector()).isNotNull();
        assertThat(dataFlow.deviceType()).isNull();
        assertThat(dataFlow.isPassThrough()).isTrue();
        assertThat(dataFlow.outputConnectors()).hasSize(1);

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/data-flows/0800000000000000");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.dataFlows().delete("0800000000000000").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/data-flows/0800000000000000");
    }
}
