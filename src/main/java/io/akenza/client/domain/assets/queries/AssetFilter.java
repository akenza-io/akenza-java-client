package io.akenza.client.domain.assets.queries;

import io.akenza.client.utils.BaseFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AssetFilter extends BaseFilter {
    public static final String ASSET_IDS = "assetIds";
    public static final String CUSTOM_FIELDS = "customFields";
    public static final String TAGS = "tags";
    public static final String IDS = "ids";

    public AssetFilter withSearch(String search) {
        parameters.put("search", search);
        return this;
    }

    public AssetFilter withIntegrationId(String integrationId) {
        parameters.put("integrationId", integrationId);
        return this;
    }

    @SuppressWarnings("unchecked")
    public AssetFilter withAssetId(String assetId) {
        Set<String> assetIds = new HashSet<>();
        if (parameters.containsKey(ASSET_IDS)) {
            assetIds = (Set<String>) parameters.get(ASSET_IDS);
        } else {
            parameters.put(ASSET_IDS, assetIds);
        }
        assetIds.add(assetId);
        return this;
    }

    @SuppressWarnings("unchecked")
    public AssetFilter withAssetIds(List<String> assetIds) {
        Set<String> existingIds = new HashSet<>();
        if (parameters.containsKey(ASSET_IDS)) {
            existingIds = (Set<String>) parameters.get(ASSET_IDS);
        } else {
            parameters.put(ASSET_IDS, assetIds);
        }
        existingIds.addAll(assetIds);
        return this;
    }

    public AssetFilter withDataFlowId(String dataFlowId) {
        parameters.put("dataFlowId", dataFlowId);
        return this;
    }

    public AssetFilter withState(String state) {
        parameters.put("state", state);
        return this;
    }

    public AssetFilter withPackageName(String packageName) {
        parameters.put("packageName", packageName);
        return this;
    }

    public AssetFilter withCustomField(String customFieldId) {
        return addId(CUSTOM_FIELDS, customFieldId);
    }

    public AssetFilter withCustomFields(List<String> customFieldIds) {
        return addIds(CUSTOM_FIELDS, customFieldIds);
    }

    public AssetFilter withTag(String tagId) {
        return addId(TAGS, tagId);
    }

    public AssetFilter withTags(List<String> tagIds) {
        return addIds(TAGS, tagIds);
    }

    @NotNull
    @SuppressWarnings("unchecked")
    private AssetFilter addId(String tags, String tagId) {
        Set<String> existingIds = new HashSet<>();
        if (parameters.containsKey(tags)) {
            existingIds = ((Map<String, Set<String>>) parameters.get(tags)).get(IDS);
        } else {
            parameters.put(tags, Map.of(IDS, existingIds));
        }
        existingIds.add(tagId);
        return this;
    }

    @NotNull
    @SuppressWarnings("unchecked")
    private AssetFilter addIds(String parameter, List<String> tagIds) {
        Set<String> existingIds = new HashSet<>();
        if (parameters.containsKey(parameter)) {
            existingIds = ((Map<String, Set<String>>) parameters.get(parameter)).get(IDS);
        } else {
            parameters.put(parameter, Map.of(IDS, existingIds));
        }
        existingIds.addAll(tagIds);
        return this;
    }
}
