package io.akenza.client.v3.domain.workspaces;

import com.google.common.io.Resources;
import io.akenza.client.http.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;
import static java.nio.charset.Charset.defaultCharset;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WorkspaceTest {

    private String fixture;

    @BeforeEach
    public void setUp() throws Exception {
        fixture = Resources.toString(getResource(this.getClass(), "workspace.json"), defaultCharset());
    }

    @Test
    public void testDeserialization() throws IOException {
        final ImmutableWorkspace workspace = Json.create().fromJson(fixture, ImmutableWorkspace.class);
        assertThat(workspace.id(), is("2900000000000000"));
        assertThat(workspace.name(), is("My Workspace"));
    }
}
