package io.akenza.client.v3.domain.custom_fields;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.custom_fields.commands.ImmutableCreateCustomFieldMetadataCommand;
import io.akenza.client.v3.domain.custom_fields.commands.ImmutableUpdateCustomFieldMetadataCommand;
import io.akenza.client.v3.domain.custom_fields.objects.CustomFieldMetaType;
import io.akenza.client.v3.domain.custom_fields.queries.CustomFieldFilter;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CustomFieldClientTest {
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
                .setBody(TestUtils.getFixture("custom_fields/custom-field-metadata.json"))
        );
        //act
        CustomFieldMetadata customField = client.customFields().getMetadataById("someDeviceConnectorId").execute();

        //assert
        assertThat(customField).isNotNull();
        assertThat(customField.id()).isEqualTo("6900000000000000");
        assertThat(customField.name()).isEqualTo("Room");
        assertThat(customField.description()).isNull();
        assertThat(customField.type()).isEqualTo(CustomFieldMetaType.STRING);
        assertThat(customField.selectOptions()).hasSize(3);
        assertThat(customField.workspaceId()).isEqualTo("2900000000000000");
        assertThat(customField.updated()).isEqualTo("2022-12-17T11:48:03.000+00:00");
        assertThat(customField.created()).isEqualTo("2022-12-17T11:48:03.000+00:00");
    }

    @Test
    void getAll() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("custom_fields/custom-field-metadata-list.json"))
        );
        //act
        CustomFieldPage page = client.customFields().listMetadata("2900000000000000", CustomFieldFilter.create()).execute();

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
                .setBody(TestUtils.getFixture("custom_fields/custom-field-metadata.json"))
        );
        //act
        CustomFieldMetadata customField = client.customFields().createMetadata(ImmutableCreateCustomFieldMetadataCommand.builder()
                .workspaceId("2900000000000000")
                .type(CustomFieldMetaType.STRING)
                .name("Room")
                .required(false)
                .addSelectOptions("Kitchen")
                .addSelectOptions("Living Room")
                .addSelectOptions("Bed Room")
                .build()).execute();

        //assert
        assertThat(customField).isNotNull();
        assertThat(customField.id()).isEqualTo("6900000000000000");
        assertThat(customField.name()).isEqualTo("Room");
        assertThat(customField.description()).isNull();
        assertThat(customField.type()).isEqualTo(CustomFieldMetaType.STRING);
        assertThat(customField.selectOptions()).hasSize(3);
        assertThat(customField.workspaceId()).isEqualTo("2900000000000000");
        assertThat(customField.updated()).isEqualTo("2022-12-17T11:48:03.000+00:00");
        assertThat(customField.created()).isEqualTo("2022-12-17T11:48:03.000+00:00");


        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/custom-fields/meta");
    }

    @Test
    void update() throws IOException, InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("custom_fields/custom-field-metadata.json"))
        );

        //act
        CustomFieldMetadata customField = client.customFields().updateMetadata(ImmutableUpdateCustomFieldMetadataCommand.builder()
                .id("6900000000000000")
                .name("Room")
                .required(false)
                .addSelectOptions("Kitchen")
                .addSelectOptions("Living Room")
                .addSelectOptions("Bed Room")
                .build()).execute();

        //assert
        assertThat(customField).isNotNull();
        assertThat(customField.id()).isEqualTo("6900000000000000");
        assertThat(customField.name()).isEqualTo("Room");
        assertThat(customField.description()).isNull();
        assertThat(customField.type()).isEqualTo(CustomFieldMetaType.STRING);
        assertThat(customField.selectOptions()).hasSize(3);
        assertThat(customField.workspaceId()).isEqualTo("2900000000000000");
        assertThat(customField.updated()).isEqualTo("2022-12-17T11:48:03.000+00:00");
        assertThat(customField.created()).isEqualTo("2022-12-17T11:48:03.000+00:00");


        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/custom-fields/meta/6900000000000000");
    }

    @Test
    void deleteMetadata() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.customFields().deleteMetadata("0700000000000001").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/custom-fields/meta/0700000000000001");
    }
}
