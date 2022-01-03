package io.akenza.client.domain.workspaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableWorkspacePage.class)
@JsonDeserialize(as = ImmutableWorkspacePage.class)
public interface WorkspacePage extends Page<Workspace> {
}
