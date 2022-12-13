package io.akenza.client.v3;

import io.akenza.client.exceptions.HttpClientResponseException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AkenzaClientTest {
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
    void unauthorized() {
        //arrange
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
}
