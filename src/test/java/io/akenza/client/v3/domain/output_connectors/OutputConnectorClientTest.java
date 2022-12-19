package io.akenza.client.v3.domain.output_connectors;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.output_connectors.commands.ImmutableCreateOutputConnectorCommand;
import io.akenza.client.v3.domain.output_connectors.commands.ImmutableUpdateOutputConnectorCommand;
import io.akenza.client.v3.domain.output_connectors.objects.ImmutableMailProperties;
import io.akenza.client.v3.domain.output_connectors.objects.OutputConnectorType;
import io.akenza.client.v3.domain.output_connectors.queries.OutputConnectorFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class OutputConnectorClientTest {
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
                .setBody(TestUtils.getFixture("output_connectors/mail-output-connector.json"))
        );
        //act
        OutputConnector outputConnector = client.outputConnectors().getById("0900000000000001").execute();

        //assert
        assertThat(outputConnector).isNotNull();
        assertThat(outputConnector.id()).isEqualTo("0900000000000001");
        assertThat(outputConnector.name()).isEqualTo("Mail Connector");
        assertThat(outputConnector.workspaceId()).isEqualTo("2900000000000000");
        assertThat(outputConnector.updated()).isNull();
        assertThat(outputConnector.mailProperties()).isNotNull();
        assertThat(outputConnector.mailProperties().messageTemplate()).isEqualTo("{{data}}");
        assertThat(outputConnector.mailProperties().recipients()).hasSize(1);
        assertThat(outputConnector.created()).isEqualTo("2022-07-27T11:09:09.000+00:00");
    }

    @Test
    void getAll() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("output_connectors/output-connector-list.json"))
        );
        //act
        OutputConnectorPage page = client.outputConnectors().list("someWorkspaceId", OutputConnectorFilter.create().withType(OutputConnectorType.AMAZON_KINESIS)).execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(4);

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/output-connectors");
        assertThat(recordedRequest.getRequestUrl().url().getQuery()).isEqualTo("type=AMAZON_KINESIS&workspaceId=someWorkspaceId");
    }

    @Test
    void create() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("output_connectors/mail-output-connector.json"))
        );
        //act
        OutputConnector outputConnector = client.outputConnectors().create(ImmutableCreateOutputConnectorCommand.builder()
                .name("My Connector")
                .type(OutputConnectorType.MAIL)
                .workspaceId("2900000000000000")
                .mailProperties(ImmutableMailProperties.builder()
                        .addRecipients("docs@akenza.io")
                        .messageTemplate("test message {{data}}")
                        .subjectTemplate("test subject")
                        .build())
                .build()).execute();

        //assert
        assertThat(outputConnector).isNotNull();
        assertThat(outputConnector.id()).isEqualTo("0900000000000001");
        assertThat(outputConnector.name()).isEqualTo("Mail Connector");
        assertThat(outputConnector.workspaceId()).isEqualTo("2900000000000000");
        assertThat(outputConnector.updated()).isNull();
        assertThat(outputConnector.mailProperties()).isNotNull();
        assertThat(outputConnector.mailProperties().messageTemplate()).isEqualTo("{{data}}");
        assertThat(outputConnector.mailProperties().recipients()).hasSize(1);
        assertThat(outputConnector.created()).isEqualTo("2022-07-27T11:09:09.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/output-connectors");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("output_connectors/mail-output-connector.json"))
        );

        //act
        OutputConnector outputConnector = client.outputConnectors().update(ImmutableUpdateOutputConnectorCommand.builder()
                .name("My Connector")
                .id("0900000000000001")
                .mailProperties(ImmutableMailProperties.builder()
                        .addRecipients("docs@akenza.io")
                        .messageTemplate("test message {{data}}")
                        .subjectTemplate("test subject")
                        .build())
                .build()).execute();

        //assert
        assertThat(outputConnector).isNotNull();
        assertThat(outputConnector.id()).isEqualTo("0900000000000001");
        assertThat(outputConnector.name()).isEqualTo("Mail Connector");
        assertThat(outputConnector.workspaceId()).isEqualTo("2900000000000000");
        assertThat(outputConnector.updated()).isNull();
        assertThat(outputConnector.mailProperties()).isNotNull();
        assertThat(outputConnector.mailProperties().messageTemplate()).isEqualTo("{{data}}");
        assertThat(outputConnector.mailProperties().recipients()).hasSize(1);
        assertThat(outputConnector.created()).isEqualTo("2022-07-27T11:09:09.000+00:00");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/output-connectors/0900000000000001");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.outputConnectors().delete("0900000000000001").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/output-connectors/0900000000000001");
    }
}
