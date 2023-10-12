package io.akenza.client.v3.domain.operations;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Instant;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class OperationClientTest {
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
                .setBody(TestUtils.getFixture("operations/operation-actions-success.json"))
        );
        //act
        Operation operation = client.operations().getById("6600000000000000").execute();

        //assert
        assertThat(operation).isNotNull();
        assertThat(operation.id()).isEqualTo("6600000000000000");
        assertThat(operation.creator()).isEqualTo("5200000000000000");
        assertThat(operation.workspaceId()).isEqualTo("2900000000000000");
        assertThat(operation.totalActionsRequired()).isEqualTo(1);
        assertThat(operation.actionsCompleted()).isEqualTo(1);
        assertThat(operation.actions()).hasSize(1);
        assertThat(operation.succeeded()).isTrue();
        assertThat(operation.started()).isEqualTo(Instant.parse("2022-12-17T12:12:26.000+00:00"));
        assertThat(operation.completed()).isEqualTo(Instant.parse("2022-12-17T12:12:26.000+00:00"));
    }

    @Test
    void getByIdFailed() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("operations/operation-actions-fail.json"))
        );
        //act
        Operation operation = client.operations().getById("6600000000000000").execute();

        //assert
        assertThat(operation).isNotNull();
        assertThat(operation.id()).isEqualTo("6600000000000000");
        assertThat(operation.creator()).isEqualTo("5200000000000000");
        assertThat(operation.workspaceId()).isEqualTo("2900000000000000");
        assertThat(operation.totalActionsRequired()).isEqualTo(1);
        assertThat(operation.actionsCompleted()).isEqualTo(1);
        assertThat(operation.actions()).hasSize(1);
        assertThat(operation.succeeded()).isFalse();
        assertThat(operation.started()).isEqualTo(Instant.parse("2022-12-17T12:12:26.000+00:00"));
        assertThat(operation.completed()).isEqualTo(Instant.parse("2022-12-17T12:12:26.000+00:00"));
    }
}
