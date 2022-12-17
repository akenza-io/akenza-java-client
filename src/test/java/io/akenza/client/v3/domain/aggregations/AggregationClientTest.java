package io.akenza.client.v3.domain.aggregations;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.aggregations.objects.AccumulatorType;
import io.akenza.client.v3.domain.aggregations.objects.ImmutableInterval;
import io.akenza.client.v3.domain.aggregations.objects.KpiAggregationResult;
import io.akenza.client.v3.domain.aggregations.objects.TimeSeriesAggregationResult;
import io.akenza.client.v3.domain.aggregations.queries.ImmutableAggregationQuery;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AggregationClientTest {
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
    void timeSeriesAggregated() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("aggregations/hourly-time-series-aggregation.json"))
        );
        //act
        TimeSeriesAggregationResult timeSeries = client.aggregations().timeSeries("0200000000000000", ImmutableAggregationQuery.builder()
                .dataKey("co2")
                .accumulator(AccumulatorType.AVG)
                .topic("default")
                .interval(ImmutableInterval.builder().from(Instant.now().minus(15, ChronoUnit.DAYS)).to(Instant.now()).build())
                .build()).execute();

        //assert
        assertThat(timeSeries).isNotNull();
        assertThat(timeSeries.deviceId()).isEqualTo("0200000000000000");
        assertThat(timeSeries.dataKey()).isEqualTo("co2");
        assertThat(timeSeries.topic()).isEqualTo("default");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/query/aggregated/hourly/time-series");
    }

    @Test
    void timeSeriesRaw() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("aggregations/raw-time-series-aggregation.json"))
        );
        //act
        TimeSeriesAggregationResult timeSeries = client.aggregations().timeSeries("0200000000000000", ImmutableAggregationQuery.builder()
                .dataKey("co2")
                .accumulator(AccumulatorType.AVG)
                .topic("default")
                .interval(ImmutableInterval.builder().from(Instant.now().minus(15, ChronoUnit.HOURS)).to(Instant.now()).build())
                .build()).execute();

        //assert
        assertThat(timeSeries).isNotNull();
        assertThat(timeSeries.deviceId()).isEqualTo("0200000000000000");
        assertThat(timeSeries.dataKey()).isEqualTo("co2");
        assertThat(timeSeries.topic()).isEqualTo("default");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/query/raw/time-series");
    }

    @Test
    void kpiAggregated() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("aggregations/hourly-kpi-aggregation.json"))
        );
        //act
        KpiAggregationResult kpi = client.aggregations().kpi("0200000000000000", ImmutableAggregationQuery.builder()
                .dataKey("co2")
                .accumulator(AccumulatorType.AVG)
                .topic("default")
                .interval(ImmutableInterval.builder().from(Instant.now().minus(15, ChronoUnit.DAYS)).to(Instant.now()).build())
                .build()).execute();

        //assert
        assertThat(kpi).isNotNull();
        assertThat(kpi.deviceId()).isEqualTo("0200000000000000");
        assertThat(kpi.dataKey()).isEqualTo("co2");
        assertThat(kpi.topic()).isEqualTo("default");
        assertThat(kpi.kpi()).isEqualTo(628.770056980057);

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/query/aggregated/hourly/kpi");
    }

    @Test
    void kpiRaw() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("aggregations/raw-kpi-aggregation.json"))
        );
        //act
        KpiAggregationResult kpi = client.aggregations().kpi("0200000000000000", ImmutableAggregationQuery.builder()
                .dataKey("co2")
                .accumulator(AccumulatorType.AVG)
                .topic("default")
                .interval(ImmutableInterval.builder().from(Instant.now().minus(15, ChronoUnit.HOURS)).to(Instant.now()).build())
                .build()).execute();

        //assert
        assertThat(kpi).isNotNull();
        assertThat(kpi.deviceId()).isEqualTo("0200000000000000");
        assertThat(kpi.dataKey()).isEqualTo("co2");
        assertThat(kpi.topic()).isEqualTo("default");
        assertThat(kpi.kpi()).isEqualTo(628.770056980057);

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/devices/0200000000000000/query/raw/kpi");
    }
}
