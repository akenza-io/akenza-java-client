package io.akenza.client.v3.domain.tags;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.tags.commands.ImmutableCreateTagCommand;
import io.akenza.client.v3.domain.tags.commands.ImmutableUpdateTagCommand;
import io.akenza.client.v3.domain.tags.queries.TagFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class TagClientTest {
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
    void getById() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("tags/tag.json"))
        );
        //act
        Tag tag = client.tags().getById("7100000000000001").execute();

        //assert
        assertThat(tag).isNotNull();
        assertThat(tag.id()).isEqualTo("7100000000000001");
        assertThat(tag.name()).isEqualTo("co2");
        assertThat(tag.description()).isNull();
        assertThat(tag.color()).isNull();
        assertThat(tag.workspaceId()).isEqualTo("2900000000000000");
        assertThat(tag.updated()).isEqualTo("2022-05-25T17:40:43.000+00:00");
        assertThat(tag.created()).isEqualTo("2022-05-25T17:40:43.000+00:00");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("tags/tag-list.json"))
        );
        //act
        TagPage page = client.tags().list("2900000000000000", TagFilter.create()).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(2);
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("tags/tag.json"))
        );
        //act
        Tag tag = client.tags().create(ImmutableCreateTagCommand.builder().workspaceId("2900000000000000").name("co2").build()).execute();

        //assert
        assertThat(tag).isNotNull();
        assertThat(tag.id()).isEqualTo("7100000000000001");
        assertThat(tag.name()).isEqualTo("co2");
        assertThat(tag.description()).isNull();
        assertThat(tag.color()).isNull();
        assertThat(tag.workspaceId()).isEqualTo("2900000000000000");
        assertThat(tag.updated()).isEqualTo("2022-05-25T17:40:43.000+00:00");
        assertThat(tag.created()).isEqualTo("2022-05-25T17:40:43.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/tags");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("tags/tag.json"))
        );

        //act
        Tag tag = client.tags().update(ImmutableUpdateTagCommand.builder().name("co2").id("7100000000000001").build()).execute();

        //assert
        assertThat(tag).isNotNull();
        assertThat(tag.id()).isEqualTo("7100000000000001");
        assertThat(tag.name()).isEqualTo("co2");
        assertThat(tag.description()).isNull();
        assertThat(tag.color()).isNull();
        assertThat(tag.workspaceId()).isEqualTo("2900000000000000");
        assertThat(tag.updated()).isEqualTo("2022-05-25T17:40:43.000+00:00");
        assertThat(tag.created()).isEqualTo("2022-05-25T17:40:43.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/tags/7100000000000001");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.tags().delete("7100000000000001").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/tags/7100000000000001");
    }
}
