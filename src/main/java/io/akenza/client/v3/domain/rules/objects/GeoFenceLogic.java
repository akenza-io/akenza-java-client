package io.akenza.client.v3.domain.rules.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableGeoFenceLogic.class)
@JsonDeserialize(as = ImmutableGeoFenceLogic.class)
@AkenzaStyle
public interface GeoFenceLogic {
    String address();

    Map<String, Object> geoJson();

    /**
     * actions to be triggered if the asset is inside the fence
     */
    List<String> insideActionIds();

    /**
     * actions to be triggered if the asset is outside the fence
     */
    List<String> outsideActionIds();
}
