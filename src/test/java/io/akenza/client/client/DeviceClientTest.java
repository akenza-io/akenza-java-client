package io.akenza.client.client;

import io.akenza.client.AkenzaAPI;
import io.akenza.client.TestUtils;
import io.akenza.client.domain.devices.DevicePage;
import io.akenza.client.domain.devices.commands.create.ImmutableCreateDeviceCommand;
import io.akenza.client.domain.devices.commands.update.ImmutableUpdateDeviceCommand;
import io.akenza.client.domain.devices.objects.Device;
import io.akenza.client.domain.devices.objects.enums.Connectivity;
import io.akenza.client.domain.devices.queries.DeviceFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeviceClientTest {
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
    void getDevices() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("devices/device-list.json"))
        );

        //act
        DevicePage dataList = client.deviceClient().list(DeviceFilter.create().withPageNumber(1).withAssetIds(List.of("id", "id2")),
                "workspaceId", "orgId").execute();

        //assert
        assertThat(dataList).isNotNull();
        assertThat(dataList.content()).hasSize(7);
        RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath())
                .contains("page=1")
                .contains("assetIds=%5Bid%2C%20id2%5D")
                .contains("workspaceId=workspaceId")
                .contains("organizationId=orgId")
                .contains("type=DEVICE")
                .startsWith("/v3/assets");
        assertThat(recordedRequest.getMethod()).isEqualTo("GET");
    }

    @Test
    void getDevice() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("devices/device-get.json")));

        //act
        Device device = client.deviceClient().getById("deviceId").execute();

        //assert
        assertThat(device).isNotNull();
        RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo("/v3/assets/deviceId");
        assertThat(recordedRequest.getMethod()).isEqualTo("GET");
    }

    @Test
    void updateDevice() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("devices/device-create-update.json")));

        //act
        Device device = client.deviceClient().update(ImmutableUpdateDeviceCommand.builder()
                .id("deviceId")
                .deviceId("deviceEui")
                .name("deviceName")
                .integrationId("integrationId")
                .connectivity(Connectivity.COAP)
                .build()).execute();

        //assert
        assertThat(device).isNotNull();
        RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo("/v3/devices/deviceId");
        assertThat(recordedRequest.getMethod()).isEqualTo("PUT");
    }

    @Test
    void createDevice() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("devices/device-get.json")));

        //act
        Device device = client.deviceClient().create(ImmutableCreateDeviceCommand.builder()
                .deviceId("deviceEui")
                .name("deviceName")
                .integrationId("integrationId")
                .workspaceId("workspaceId")
                .connectivity(Connectivity.COAP)
                .build()).execute();

        //assert
        assertThat(device).isNotNull();
        RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo("/v3/devices/");
        assertThat(recordedRequest.getMethod()).isEqualTo("POST");
    }

    @Test
    void deleteDevice() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204));

        //act
        client.deviceClient().delete("deviceId").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest();
        assertThat(recordedRequest.getPath()).isEqualTo("/v3/devices/deviceId");
        assertThat(recordedRequest.getMethod()).isEqualTo("DELETE");
    }
}
