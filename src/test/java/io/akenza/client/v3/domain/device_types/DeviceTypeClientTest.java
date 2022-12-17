package io.akenza.client.v3.domain.device_types;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.device_types.commands.ImmutableCreateDeviceTypeCommand;
import io.akenza.client.v3.domain.device_types.commands.ImmutableUpdateDeviceTypeCommand;
import io.akenza.client.v3.domain.device_types.objects.ImmutableDeviceTypeMetadata;
import io.akenza.client.v3.domain.device_types.objects.ImmutableScript;
import io.akenza.client.v3.domain.device_types.queries.DeviceTypeFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeviceTypeClientTest {
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
    void getById() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_types/device-type.json"))
        );
        //act
        DeviceType deviceType = client.deviceTypes().getById("33dd62d6487c263c").execute();

        //assert
        assertThat(deviceType).isNotNull();
        assertThat(deviceType.id()).isEqualTo("33dd62d6487c263c");
        assertThat(deviceType.name()).isEqualTo("DL-ATM41");
        assertThat(deviceType.description()).isEqualTo("All-in-one weather station with sensors for solar radiation, precipitation, air temperature, relative humidity, indoor temperature, vapor pressure, air pressure, barometric pressure, wind speed and direction, maximum wind speed, tilt sensor, number of lightning strikes and average lightning distance.");
        assertThat(deviceType.version()).isEqualTo(0);
        assertThat(deviceType.uplinkScript().code()).isNotNull();
        assertThat(deviceType.downlinkScript()).isNotNull();
        assertThat(deviceType.downlinkScript().code()).isNull();
        assertThat(deviceType.schemas().size()).isEqualTo(2);
        assertThat(deviceType.organizationId()).isNull();
        assertThat(deviceType.type()).isEqualTo("library");
        assertThat(deviceType.created()).isEqualTo("2022-12-13T20:08:01.955048601Z");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-types/33dd62d6487c263c");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_types/device-types-list.json"))
        );
        //act
        DeviceTypePage page = client.deviceTypes().list("someOrganizationId", DeviceTypeFilter.create()).execute();

        //assert
        assertThat(page).isNotNull();
        AssertionsForInterfaceTypes.assertThat(page.content()).hasSize(20);
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_types/custom-device-type.json"))
        );
        //act
        DeviceType deviceType = client.deviceTypes().create(ImmutableCreateDeviceTypeCommand.builder()
                .name("Sensecap")
                .organizationId("2800000000000000")
                .uplinkScript(ImmutableScript.builder().code("someCode").build())
                .putSchemas("default", "{}")
                .meta(ImmutableDeviceTypeMetadata.builder().manufacturer("Akenza").build())
                .build()).execute();

        //assert
        assertThat(deviceType).isNotNull();
        assertThat(deviceType.id()).isEqualTo("33dbb14a0b86fc90");
        assertThat(deviceType.name()).isEqualTo("Sensecap");
        assertThat(deviceType.description()).isNull();
        assertThat(deviceType.version()).isZero();
        assertThat(deviceType.organizationId()).isEqualTo("2800000000000000");
        assertThat(deviceType.created()).isEqualTo("2022-12-13T20:33:18.372745521Z");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-types");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_types/custom-device-type.json"))
        );
        //act
        DeviceType deviceType = client.deviceTypes().update(ImmutableUpdateDeviceTypeCommand.builder()
                .id("33dbb14a0b86fc90")
                .name("Sensecap")
                .organizationId("2800000000000000")
                .uplinkScript(ImmutableScript.builder().code("someCode").build())
                .putSchemas("default", "{}")
                .meta(ImmutableDeviceTypeMetadata.builder().manufacturer("Akenza").build())
                .build()).execute();

        //assert
        assertThat(deviceType).isNotNull();
        assertThat(deviceType.id()).isEqualTo("33dbb14a0b86fc90");
        assertThat(deviceType.name()).isEqualTo("Sensecap");
        assertThat(deviceType.description()).isNull();
        assertThat(deviceType.version()).isZero();
        assertThat(deviceType.organizationId()).isEqualTo("2800000000000000");
        assertThat(deviceType.created()).isEqualTo("2022-12-13T20:33:18.372745521Z");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-types/33dbb14a0b86fc90");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.deviceTypes().delete("33dbb14a0b86fc90").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-types/33dbb14a0b86fc90");
    }
}
