package io.akenza.client.client;

import io.akenza.client.AkenzaAPI;
import io.akenza.client.TestUtils;
import io.akenza.client.domain.organizations.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
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
        Organization organization = client.organizations().getById("someOrg").execute();

        //assert
        checkOrganization(organization);
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/organizations/someOrg");
    }

    @Test
    void getAll() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("organizations/organization-list.json"))
        );
        //act
        OrganizationPage page = client.organizations().list(OrganizationFilter.create().withName("name")).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(24);
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/organizations/");
    }

    @Test
    void create() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("organizations/organization.json"))
        );
        //act
        Organization organization = client.organizations().create(ImmutableCreateOrganizationCommand.builder().name("My Workspace").build()).execute();

        //assert
        checkOrganization(organization);
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/organizations/");
    }

    @Test
    void update() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("organizations/organization.json"))
        );
        //act
        Organization organization = client.organizations().update(ImmutableUpdateOrganizationCommand.builder().id("2900000000000000").name("a").build()).execute();

        //assert
        checkOrganization(organization);
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/organizations/2900000000000000");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.organizations().delete("2900000000000000").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/organizations/2900000000000000");
    }

    private void checkOrganization(Organization organization) {
        assertThat(organization).isNotNull();
        assertThat(organization.id()).isEqualTo("2800000000000000");
        assertThat(organization.name()).isEqualTo("A");
        assertThat(organization.description()).isEqualTo("description");
        assertThat(organization.owner().id()).isEqualTo("5200000000000000");
        assertThat(organization.owner().name()).isEqualTo("foo bar");
        assertThat(organization.owner().email()).isEqualTo("foo@akenza.io");
        assertThat(organization.logo()).isEqualTo("data:image/png;base64,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");
        assertThat(organization.updated()).isEqualTo("2021-04-01T08:09:06.000+00:00");
        assertThat(organization.created()).isEqualTo("2021-03-18T13:32:35.000+00:00");
    }
}
