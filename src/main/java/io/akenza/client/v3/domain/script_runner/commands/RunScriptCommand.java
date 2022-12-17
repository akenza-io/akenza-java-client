package io.akenza.client.v3.domain.script_runner.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableRunScriptCommand.class)
@JsonDeserialize(as = ImmutableRunScriptCommand.class)
@AkenzaStyle
public abstract class RunScriptCommand {
    /**
     * the script code
     */
    public abstract String script();

    /**
     * uplink event that is passed to the script
     */
    public abstract Map<String, Object> event();
}

