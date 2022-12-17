package io.akenza.client.v3.domain.organizations;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class OrganizationClientTest {
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
    void getById() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("organizations/organization.json"))
        );
        //act
        Organization organization = client.organizations().getById("2800000000000000").execute();

        //assert
        assertThat(organization).isNotNull();
        assertThat(organization.id()).isEqualTo("2800000000000000");
        assertThat(organization.name()).isEqualTo("Test Organization");
        assertThat(organization.description()).isEqualTo("Test Org");
        assertThat(organization.logo()).isNotEmpty();
        //TODO add once available
        //assertThat(organization.version()).isEqualTo(1);
        assertThat(organization.properties().size()).isEqualTo(0);
        assertThat(organization.updated()).isEqualTo("2022-03-02T13:17:31.000+00:00");
        assertThat(organization.created()).isEqualTo("2022-03-02T09:08:16.000+00:00");

        var recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/organizations/2800000000000000");
    }
}
