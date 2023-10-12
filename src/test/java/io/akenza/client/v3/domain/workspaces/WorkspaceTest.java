package io.akenza.client.v3.domain.workspaces;

import io.akenza.client.TestUtils;
import io.akenza.client.http.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WorkspaceTest {

    private String fixture;

    @BeforeEach
    public void setUp() throws Exception {
        fixture = TestUtils.getFixture("workspaces/workspace.json");
    }

    @Test
    public void testDeserialization() throws IOException {
        final ImmutableWorkspace workspace = Json.create().fromJson(fixture, ImmutableWorkspace.class);
        assertThat(workspace.id(), is("2900000000000000"));
        assertThat(workspace.name(), is("My Workspace"));
    }
}
