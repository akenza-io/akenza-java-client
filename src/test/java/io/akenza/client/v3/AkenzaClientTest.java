package io.akenza.client.v3;

import io.akenza.client.TestUtils;
import io.akenza.client.exceptions.HttpClientResponseException;
import io.akenza.client.http.ImmutableAuthOptions;
import io.akenza.client.http.ImmutableHttpOptions;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AkenzaClientTest {
    private MockWebServer server;

    @BeforeEach
    public void setup() {
        server = new MockWebServer();
    }

    @AfterEach
    public void teardown() throws IOException {
        server.shutdown();
    }

    @Test
    void unauthorized() {
        //arrange
        var client = AkenzaAPI.create(String.format("http://localhost:%s", server.getPort()), "apiKey");
        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setHeader("Content-Type", "application/json")
                .setBody("{\n  \"message\": \"Unauthorized\"\n}")
        );

        try {
            //act
            client.workspaces().getById("someWorkspaceId").execute();
            Assertions.fail("Did not throw");
        } catch (HttpClientResponseException ex) {
            //assert
            assertThat(ex.statusCode()).isEqualTo(401);
            assertThat(ex.getMessage()).isEqualTo("Unauthorized");
            assertThat(ex.path()).isEqualTo("/v3/workspaces/someWorkspaceId");
            assertThat(ex.values().size()).isEqualTo(1);
        }
    }

    @Test
    void ok() throws InterruptedException {
        //arrange
        var client = AkenzaAPI.create(String.format("http://localhost:%s", server.getPort()), "apiKey");
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("workspaces/workspace.json"))
        );

        //act
        client.workspaces().getById("someWorkspaceId").execute();

        //assert
        var request = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(request.getHeader("x-api-key")).isEqualTo("apiKey");

        var telemetry = request.getHeader("Akenza-Client");
        assertThat(telemetry).isNotNull();

        var telemetryData = new String(Base64.getDecoder().decode(telemetry));
        assertThat(telemetryData).isEqualTo("{\"name\":\"akenza-java-client\",\"env\":{\"java\":\"18\"}}");
    }

    @Test
    void okWithBearer() throws InterruptedException {
        //arrange
        var client = AkenzaAPI.create(ImmutableHttpOptions.builder()
                .baseUrl(String.format("http://localhost:%s", server.getPort()))
                .authOptions(ImmutableAuthOptions.builder().bearerToken("someBearerToken").build())
                .build());
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("workspaces/workspace.json"))
        );

        //act
        client.workspaces().getById("someWorkspaceId").execute();

        //assert
        var request = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(request.getHeader("Authorization")).isEqualTo("Bearer someBearerToken");
    }
    
    //TODO test logging
    //TODO test exception handling
}
