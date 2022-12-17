package io.akenza.client.v3.domain.downlinks;

import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.downlinks.commands.ImmutableDownlinkCommand;
import io.akenza.client.v3.domain.downlinks.objects.ImmutableLoRaDownlink;
import io.akenza.client.v3.domain.downlinks.objects.ImmutableMqttDownlink;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DownlinkClientTest {
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
    void sendMqttDownlink() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
        );

        //act
        client.downlinks().send("0200000000000000", ImmutableDownlinkCommand.builder()
                        .mqttDownlink(ImmutableMqttDownlink.builder().topic("/command").payload(Map.of("key", "value")).build()).build())
                .execute();

        //assert

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/downlink");
    }


    @Test
    void sendLoRaDownlink() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
        );

        //act
        client.downlinks().send("0200000000000000", ImmutableDownlinkCommand.builder()
                        .raw(true)
                        .loraDownlink(ImmutableLoRaDownlink.builder().clearQueue(true).port(1).payloadHex("FFFFFFFF").build()).build())
                .execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/downlink");
    }
}
