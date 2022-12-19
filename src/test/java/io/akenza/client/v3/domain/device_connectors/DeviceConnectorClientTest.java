package io.akenza.client.v3.domain.device_connectors;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.common.Connectivity;
import io.akenza.client.v3.domain.device_connectors.commands.ImmutableCreateDeviceConnectorCommand;
import io.akenza.client.v3.domain.device_connectors.commands.ImmutableUpdateDeviceConnectorCommand;
import io.akenza.client.v3.domain.device_connectors.queries.DeviceConnectorFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DeviceConnectorClientTest {
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
                .setBody(TestUtils.getFixture("device_connectors/device-connector.json"))
        );
        //act
        DeviceConnector deviceConnector = client.deviceConnectors().getById("someDeviceConnectorId").execute();

        //assert
        assertThat(deviceConnector).isNotNull();
        assertThat(deviceConnector.id()).isEqualTo("0700000000000001");
        assertThat(deviceConnector.name()).isEqualTo("HTTP Device Connector");
        assertThat(deviceConnector.description()).isNull();
        assertThat(deviceConnector.version()).isEqualTo(0);
        assertThat(deviceConnector.workspaceId()).isEqualTo("2900000000000000");
        assertThat(deviceConnector.updated()).isNull();
        assertThat(deviceConnector.created()).isEqualTo("2022-08-02T15:31:49.000+00:00");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_connectors/device-connector-list.json"))
        );
        //act
        DeviceConnectorPage page = client.deviceConnectors().list("someOrganizationId", DeviceConnectorFilter.create()).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(2);
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_connectors/device-connector.json"))
        );
        //act
        DeviceConnector deviceConnector = client.deviceConnectors().create(ImmutableCreateDeviceConnectorCommand.builder()
                .name("My Connector")
                .connectivity(Connectivity.HTTP)
                .workspaceId("2900000000000000")
                .build()).execute();

        //assert
        assertThat(deviceConnector).isNotNull();
        assertThat(deviceConnector).isNotNull();
        assertThat(deviceConnector.id()).isEqualTo("0700000000000001");
        assertThat(deviceConnector.name()).isEqualTo("HTTP Device Connector");
        assertThat(deviceConnector.description()).isNull();
        assertThat(deviceConnector.version()).isEqualTo(0);
        assertThat(deviceConnector.workspaceId()).isEqualTo("2900000000000000");
        assertThat(deviceConnector.updated()).isNull();
        assertThat(deviceConnector.created()).isEqualTo("2022-08-02T15:31:49.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-connectors");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_connectors/device-connector.json"))
        );

        //act
        DeviceConnector deviceConnector = client.deviceConnectors().update(ImmutableUpdateDeviceConnectorCommand.builder()
                        .id("0700000000000001")
                        .name("My Workspace")
                        .build())
                .execute();

        //assert
        assertThat(deviceConnector).isNotNull();
        assertThat(deviceConnector).isNotNull();
        assertThat(deviceConnector.id()).isEqualTo("0700000000000001");
        assertThat(deviceConnector.name()).isEqualTo("HTTP Device Connector");
        assertThat(deviceConnector.description()).isNull();
        assertThat(deviceConnector.version()).isEqualTo(0);
        assertThat(deviceConnector.workspaceId()).isEqualTo("2900000000000000");
        assertThat(deviceConnector.updated()).isNull();
        assertThat(deviceConnector.created()).isEqualTo("2022-08-02T15:31:49.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-connectors/0700000000000001");
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
