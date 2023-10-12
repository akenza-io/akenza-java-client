package io.akenza.client.v3.domain.device_credentials;

import io.akenza.client.TestUtils;
import io.akenza.client.v3.AkenzaAPI;
import io.akenza.client.v3.domain.device_credentials.commands.ImmutableCreateDeviceCredentialCommand;
import io.akenza.client.v3.domain.device_credentials.objects.Algorithm;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DeviceCredentialsClientTest {
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
    void getAll() {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_credentials/device-credentials-list.json"))
        );
        //act
        DeviceCredentialPage page = client.deviceCredentials().list("0200000000000000").execute();

        //assert
        assertThat(page).isNotNull();
        assertThat(page.content()).hasSize(1);
    }

    @Test
    void create() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(201)
                .setHeader("Content-Type", "application/json")
                .setBody(TestUtils.getFixture("device_credentials/device-credential.json"))
        );
        //act
        DeviceCredential deviceCredential = client.deviceCredentials().create(ImmutableCreateDeviceCredentialCommand.builder()
                .name("Primary")
                .akenzaDeviceId("0200000000000000")
                .algorithm(Algorithm.EC256_X509_PEM)
                .publicKey("-----BEGIN CERTIFICATE-----\\nMIIBtzCCAV4CCQCFuOdmw61DiTAKBggqhkjOPQQDAjBkMQswCQYDVQQGEwJDSDEL\\nMAkGA1UECAwCWkgxDzANBgNVBAcMBlp1cmljaDEPMA0GA1UECgwGQWtlbnphMQ0w\\nCwYDVQQLDAREb2NzMRcwFQYDVQQDDA4wQTFGRkZGRkZGRkZGRjAeFw0yMjEyMTcx\\nMDQyMzdaFw0yMzAxMTYxMDQyMzdaMGQxCzAJBgNVBAYTAkNIMQswCQYDVQQIDAJa\\nSDEPMA0GA1UEBwwGWnVyaWNoMQ8wDQYDVQQKDAZBa2VuemExDTALBgNVBAsMBERv\\nY3MxFzAVBgNVBAMMDjBBMUZGRkZGRkZGRkZGMFkwEwYHKoZIzj0CAQYIKoZIzj0D\\nAQcDQgAEAMWCIzOKLbAFMWLR3AYyU8lWikFALSlHb7vz0SECkfZYuTJ9XJd22epG\\nqi9yDaSH40yWtRl8D3ycpqm4J/9fSzAKBggqhkjOPQQDAgNHADBEAiAVbpXBJP3R\\nJsywaKGmxzSKWevlWZwJvMv4vzhHPEQsIAIgXDsKMRJQgXeNO1HZUNQEFqXmFW3m\\nXXrttVVkwQOYYgA=\\n-----END CERTIFICATE-----\\n")
                .build()).execute();

        //assert
        assertThat(deviceCredential).isNotNull();
        assertThat(deviceCredential.id()).isEqualTo("9900000000000000");
        assertThat(deviceCredential.name()).isEqualTo("Primary");
        assertThat(deviceCredential.deviceId()).isEqualTo("0A1FFFFFFFFFFF");
        assertThat(deviceCredential.akenzaDeviceId()).isEqualTo("0200000000000000");
        assertThat(deviceCredential.algorithm()).isEqualTo(Algorithm.EC256_X509_PEM);
        assertThat(deviceCredential.fingerprint()).isEqualTo("999e75132e2c420dce920612c11c3f3369ee7ed2");
        assertThat(deviceCredential.publicKey()).isEqualTo("-----BEGIN CERTIFICATE-----\\nMIIBtzCCAV4CCQCFuOdmw61DiTAKBggqhkjOPQQDAjBkMQswCQYDVQQGEwJDSDEL\\nMAkGA1UECAwCWkgxDzANBgNVBAcMBlp1cmljaDEPMA0GA1UECgwGQWtlbnphMQ0w\\nCwYDVQQLDAREb2NzMRcwFQYDVQQDDA4wQTFGRkZGRkZGRkZGRjAeFw0yMjEyMTcx\\nMDQyMzdaFw0yMzAxMTYxMDQyMzdaMGQxCzAJBgNVBAYTAkNIMQswCQYDVQQIDAJa\\nSDEPMA0GA1UEBwwGWnVyaWNoMQ8wDQYDVQQKDAZBa2VuemExDTALBgNVBAsMBERv\\nY3MxFzAVBgNVBAMMDjBBMUZGRkZGRkZGRkZGMFkwEwYHKoZIzj0CAQYIKoZIzj0D\\nAQcDQgAEAMWCIzOKLbAFMWLR3AYyU8lWikFALSlHb7vz0SECkfZYuTJ9XJd22epG\\nqi9yDaSH40yWtRl8D3ycpqm4J/9fSzAKBggqhkjOPQQDAgNHADBEAiAVbpXBJP3R\\nJsywaKGmxzSKWevlWZwJvMv4vzhHPEQsIAIgXDsKMRJQgXeNO1HZUNQEFqXmFW3m\\nXXrttVVkwQOYYgA=\\n-----END CERTIFICATE-----\\n");
        assertThat(deviceCredential.created()).isEqualTo("2022-10-31T08:11:14Z");

        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-authorization/devices/0200000000000000/credentials");
    }

    @Test
    void delete() throws InterruptedException {
        //arrange
        server.enqueue(new MockResponse()
                .setResponseCode(204)
                .setHeader("Content-Type", "application/json")
        );

        //act
        client.deviceCredentials().delete("0200000000000000", "9900000000000000").execute();

        //assert
        RecordedRequest recordedRequest = server.takeRequest(2, TimeUnit.SECONDS);
        assertThat(recordedRequest.getRequestUrl().url().getPath()).isEqualTo("/v3/device-authorization/devices/0200000000000000/credentials/9900000000000000");
    }
}
