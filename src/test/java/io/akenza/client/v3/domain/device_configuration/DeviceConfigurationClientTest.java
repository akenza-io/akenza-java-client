package io.akenza.client.v3.domain.device_configuration;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.common.MessageResponse;
import io.akenza.client.v3.domain.device_configuration.commands.ImmutableCreateDeviceConfigurationCommand;
import io.akenza.client.v3.domain.device_configuration.queries.DeviceConfigurationFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DeviceConfigurationClientTest {
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
    void getByVersion() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_configuration/device-configuration.json"))
        );
        //act
        DeviceConfiguration deviceConfiguration = client.deviceConfigurations().getByVersion("0200000000000000", 2).execute();

        //assert
        assertThat(deviceConfiguration).isNotNull();
        assertThat(deviceConfiguration.version()).isEqualTo(2);
        assertThat(deviceConfiguration.workspaceId()).isEqualTo("2900000000000000");
        assertThat(deviceConfiguration.deviceId()).isEqualTo("0200000000000000");
        assertThat(deviceConfiguration.configuration()).containsKey("key");
        assertThat(deviceConfiguration.created()).isEqualTo(Instant.parse("2022-07-28T15:55:50.257+00:00"));
    }

    @Test
    void getLatest() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_configuration/device-configuration.json"))
        );
        //act
        DeviceConfiguration deviceConfiguration = client.deviceConfigurations().getLatest("0200000000000000").execute();

        //assert
        assertThat(deviceConfiguration).isNotNull();
        assertThat(deviceConfiguration.version()).isEqualTo(2);
        assertThat(deviceConfiguration.workspaceId()).isEqualTo("2900000000000000");
        assertThat(deviceConfiguration.deviceId()).isEqualTo("0200000000000000");
        assertThat(deviceConfiguration.configuration()).containsKey("key");
        assertThat(deviceConfiguration.created()).isEqualTo(Instant.parse("2022-07-28T15:55:50.257+00:00"));

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/configuration/latest");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_configuration/device-configuration-list.json"))
        );
        //act
        DeviceConfigurationPage page = client.deviceConfigurations().list("someOrganizationId", "0200000000000000", DeviceConfigurationFilter.create()).execute();

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
                .setBody(TestUtils.getFixture("device_configuration/device-configuration.json"))
        );
        //act
        DeviceConfiguration deviceConfiguration = client.deviceConfigurations().create("0200000000000000", ImmutableCreateDeviceConfigurationCommand.builder()
                .workspaceId("2900000000000000")
                .configuration(Map.of("key", "value"))
                .build()).execute();

        //assert
        assertThat(deviceConfiguration).isNotNull();
        assertThat(deviceConfiguration.version()).isEqualTo(2);
        assertThat(deviceConfiguration.workspaceId()).isEqualTo("2900000000000000");
        assertThat(deviceConfiguration.deviceId()).isEqualTo("0200000000000000");
        assertThat(deviceConfiguration.configuration()).containsKey("key");
        assertThat(deviceConfiguration.created()).isEqualTo(Instant.parse("2022-07-28T15:55:50.257+00:00"));

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/configuration");
    }

    @Test
    void push() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("common/message-response.json"))
        );
        //act
        MessageResponse response = client.deviceConfigurations().push("0200000000000000").execute();

        //assert
        assertThat(response).isNotNull();
        assertThat(response.message()).isEqualTo("Configuration successfully sent");


        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/configuration/push");
    }


    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.deviceConfigurations().delete("0200000000000000", 0).execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/configuration/0");
    }
}
