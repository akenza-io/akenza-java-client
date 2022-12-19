package io.akenza.client.v3.domain.script_runner;

import com.fasterxml.jackson.core.type.TypeReference;
import io.akenza.client.http.HttpMethod;
import io.akenza.client.http.HttpOptions;
import io.akenza.client.http.Request;
import io.akenza.client.http.RequestImpl;
import io.akenza.client.utils.BaseClient;
import io.akenza.client.v3.domain.script_runner.commands.RunScriptCommand;
import io.akenza.client.v3.domain.script_runner.objects.ScriptRunResult;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.util.List;

/**
 * Client for testing scripts
 */
public class ScriptRunnerClient extends BaseClient {
    private static final String RUN_SCRIPT_URI_TEMPLATE = "v3/run-script";

    public ScriptRunnerClient(OkHttpClient client, HttpOptions options) {
        super(client, options);
    }

    /**
     * Runs a script against script runner used for testing scripts
     *
     * @param runScriptCommand a run script command
     * @return a list of script run results
     */
    public Request<List<ScriptRunResult>> runScript(RunScriptCommand runScriptCommand) {
        final String path = RUN_SCRIPT_URI_TEMPLATE;

        HttpUrl.Builder builder = baseUrl
                .newBuilder()
                .addPathSegments(path);

        String url = builder.build().toString();
        var request = new RequestImpl<>(client, url, HttpMethod.POST, new TypeReference<List<ScriptRunResult>>() {
        });
        addAuthentication(request);
        request.withBody(runScriptCommand);
        return request;
    }
}
