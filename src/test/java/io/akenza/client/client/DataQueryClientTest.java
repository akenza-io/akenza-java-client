package io.akenza.client.client;

import io.akenza.client.AkenzaAPI;
import io.akenza.client.TestUtils;
import io.akenza.client.domain.data.DeviceData;
import io.akenza.client.domain.data.ImmutableDataQuery;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DataQueryClientTest {
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
    void query() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("data/device-data-list.json"))
        );

        //act
        List<DeviceData> dataList = client.dataQuery().query("", ImmutableDataQuery.builder().build()).execute();

        //assert
        assertThat(dataList).isNotNull();
        assertThat(dataList).hasSize(10);
    }

    @Test
    void topics() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("data/topics.json"))
        );

        //act
        List<String> topics = client.dataQuery().queryTopics("").execute();

        //assert
        assertThat(topics).isNotNull();
        assertThat(topics).contains("default", "internalTemp", "motion");
    }

    @Test
    void queryLastSample() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("data/device-data.json"))
        );

        //act
        DeviceData sample = client.dataQuery().queryLatestSample("").execute();

        //assert
        assertThat(sample).isNotNull();
        assertThat(sample.timestamp()).isEqualTo("2022-01-16T19:11:59.858+00:00");
        assertThat(sample.topic()).isEqualTo("default");
        assertThat(sample.data()).isEqualTo(Map.of(
                "temperature", 18.4,
                "humidity", 46,
                "light", 1,
                "vdd", 3541));
    }
}
