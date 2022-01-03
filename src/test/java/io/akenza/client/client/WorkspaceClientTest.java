package io.akenza.client.client;

import com.google.common.io.Resources;
import io.akenza.client.AkenzaAPI;
import io.akenza.client.domain.workspaces.*;
import io.akenza.client.workspaces.WorkspaceTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WorkspaceClientTest {
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
    void getById() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(getFixture("workspace.json"))
        );
        //act
        Workspace workspace = client.workspaces().getById("someWorkspaceId").execute();

        //assert
        assertThat(workspace).isNotNull();
        assertThat(workspace.id()).isEqualTo("2900000000000000");
        assertThat(workspace.name()).isEqualTo("My Workspace");
        assertThat(workspace.description()).isEqualTo("Test workspace akenza");
        assertThat(workspace.version()).isEqualTo(1);
        assertThat(workspace.properties().size()).isEqualTo(0);
        assertThat(workspace.organizationId()).isEqualTo("2800000000000000");
        assertThat(workspace.updated()).isEqualTo("2021-04-01T08:09:06.000+00:00");
        assertThat(workspace.created()).isEqualTo("2021-03-18T13:32:35.000+00:00");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(getFixture("workspace-list.json"))
        );
        //act
        WorkspacePage page = client.workspaces().list("someOrganizationId", WorkspaceFilter.create()).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(10);
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(getFixture("workspace.json"))
        );
        //act
        Workspace workspace = client.workspaces().create(ImmutableCreateWorkspaceCommand.builder().name("My Workspace").organizationId("2800000000000000").build()).execute();

        //assert
        assertThat(workspace).isNotNull();
        assertThat(workspace.id()).isEqualTo("2900000000000000");
        assertThat(workspace.name()).isEqualTo("My Workspace");
        assertThat(workspace.description()).isEqualTo("Test workspace akenza");
        assertThat(workspace.version()).isEqualTo(1);
        assertThat(workspace.properties().size()).isEqualTo(0);
        assertThat(workspace.organizationId()).isEqualTo("2800000000000000");
        assertThat(workspace.updated()).isEqualTo("2021-04-01T08:09:06.000+00:00");
        assertThat(workspace.created()).isEqualTo("2021-03-18T13:32:35.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/workspaces/");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(getFixture("workspace.json"))
        );
        //act
        Workspace workspace = client.workspaces().update(ImmutableUpdateWorkspaceCommand.builder().id("2900000000000000").name("My Workspace").build()).execute();

        //assert
        assertThat(workspace).isNotNull();
        assertThat(workspace.id()).isEqualTo("2900000000000000");
        assertThat(workspace.name()).isEqualTo("My Workspace");
        assertThat(workspace.description()).isEqualTo("Test workspace akenza");
        assertThat(workspace.version()).isEqualTo(1);
        assertThat(workspace.properties().size()).isEqualTo(0);
        assertThat(workspace.organizationId()).isEqualTo("2800000000000000");
        assertThat(workspace.updated()).isEqualTo("2021-04-01T08:09:06.000+00:00");
        assertThat(workspace.created()).isEqualTo("2021-03-18T13:32:35.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/workspaces/2900000000000000");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.workspaces().delete("2900000000000000").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/workspaces/2900000000000000");
    }


    private static String getFixture(String resource) throws IOException {
        return Resources.toString(getResource(WorkspaceTest.class, resource), defaultCharset());
    }
}
