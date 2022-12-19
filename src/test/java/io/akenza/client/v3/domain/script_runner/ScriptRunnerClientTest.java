package io.akenza.client.v3.domain.script_runner;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.script_runner.commands.ImmutableRunScriptCommand;
import io.akenza.client.v3.domain.script_runner.objects.ScriptRunResult;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScriptRunnerClientTest {
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
    void runScript() throws IOException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("script_runner/script-run-result-list.json"))
        );
        //act
        List<ScriptRunResult> runResults = client.scripts().runScript(ImmutableRunScriptCommand.builder()
                .script("function consume(event) { emit(\"sample\", {data: event.foo}); emit(\"foo\", {data: event.foo}); emit(\"log\", {data: event.foo}); }")
                .event(Map.of("data", Map.of("key", "value")))
                .build()).execute();

        //assert
        assertThat(runResults).isNotNull();
        assertThat(runResults).hasSize(3);
    }
}
