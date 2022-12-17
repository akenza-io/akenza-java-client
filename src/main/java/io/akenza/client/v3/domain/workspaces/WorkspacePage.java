package io.akenza.client.v3.domain.workspaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableWorkspacePage.class)
@JsonDeserialize(as = ImmutableWorkspacePage.class)
@AkenzaStyle
public abstract class WorkspacePage implements Page<Workspace> {
    public abstract List<Workspace> content();
}
