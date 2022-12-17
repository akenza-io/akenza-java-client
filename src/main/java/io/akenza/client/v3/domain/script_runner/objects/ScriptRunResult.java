package io.akenza.client.v3.domain.script_runner.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.HashMap;

@Value.Immutable
@JsonSerialize(as = ImmutableScriptRunResult.class)
@JsonDeserialize(as = ImmutableScriptRunResult.class)
@AkenzaStyle
public interface ScriptRunResult {
    EmitType type();

    HashMap<String, Object> event();
}
