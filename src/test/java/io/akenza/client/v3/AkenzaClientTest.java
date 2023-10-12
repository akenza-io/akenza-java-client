package io.akenza.client.v3;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.TestUtils;
import io.akenza.client.exceptions.HttpClientResponseException;
import io.akenza.client.http.ImmutableAuthOptions;
import io.akenza.client.http.ImmutableHttpOptions;
import io.akenza.client.http.Json;
import io.akenza.client.http.Request;
import io.akenza.client.v3.domain.workspaces.Workspace;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.assertj.core.api.MapAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
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

        //act
        Request<Workspace> request = client.workspaces().getById("someWorkspaceId");
        try {
            request.execute();
            Assertions.fail("Did not throw");
        } catch (HttpClientResponseException ex) {
            //assert
            assertThat(ex.statusCode()).isEqualTo(401);
            assertThat(ex.getMessage()).isEqualTo("Unauthorized");
            assertThat(ex.path()).isEqualTo("/v3/workspaces/someWorkspaceId");
            MapAssert.assertThatMap(ex.values()).hasSize(1);
        }
    }

    @Test
    void ok() throws InterruptedException, IOException {
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
        var map = Json.create()
                .fromJson(telemetryData, new TypeReference<Map<String, Object>>() {
                });

        MapAssert.assertThatMap(map)
                .containsEntry("name", "akenza-java-client")
                .hasEntrySatisfying("env", Objects::requireNonNull);
        MapAssert.assertThatMap((Map<String , Object>)map.get("env"))
                .hasEntrySatisfying("java", Objects::requireNonNull);

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
