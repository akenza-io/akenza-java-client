package io.akenza.client.v3.domain.workspaces.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableWorkspaceSettings.class)
@JsonDeserialize(as = ImmutableWorkspaceSettings.class)
@AkenzaStyle
public interface WorkspaceSettings {

    /**
     * Whether lifecycle messages are enabled
     */
    @Value.Default
    default Boolean lifecycleMessages() {
        return false;
    }

    /**
     * Interval of lifecycle messages (DAILY or WEEKLY)
     */
    @Nullable
    LifecycleMessageInterval lifecycleMessageInterval();

    /**
     * Lifecycle message recipients
     */
    @Nullable
    List<String> lifecycleMessageRecipients();
}
