package io.akenza.client.v3.domain.assets.queries;

import io.akenza.client.utils.BaseFilter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AssetFilter<T extends BaseFilter<T>> extends BaseFilter<T> {
    public static final String ASSET_IDS = "assetIds";
    public static final String CUSTOM_FIELDS = "customFields";
    public static final String TAGS = "tags";
    public static final String IDS = "ids";

    public T withSearch(String search) {
        parameters.put("search", search);
        return getThis();
    }

    public T withIntegrationId(String integrationId) {
        parameters.put("integrationId", integrationId);
        return getThis();
    }

    @SuppressWarnings("unchecked")
    public T withAssetId(String assetId) {
        Set<String> assetIds = new HashSet<>();
        if (parameters.containsKey(ASSET_IDS)) {
            assetIds = (Set<String>) parameters.get(ASSET_IDS);
        } else {
            parameters.put(ASSET_IDS, assetIds);
        }
        assetIds.add(assetId);
        return getThis();
    }

    @SuppressWarnings("unchecked")
    public T withAssetIds(List<String> assetIds) {
        Set<String> existingIds = new HashSet<>();
        if (parameters.containsKey(ASSET_IDS)) {
            existingIds = (Set<String>) parameters.get(ASSET_IDS);
        } else {
            parameters.put(ASSET_IDS, assetIds);
        }
        existingIds.addAll(assetIds);
        return getThis();
    }

    public T withDataFlowId(String dataFlowId) {
        parameters.put("dataFlowId", dataFlowId);
        return getThis();
    }

    public T withState(String state) {
        parameters.put("state", state);
        return getThis();
    }

    public T withPackageName(String packageName) {
        parameters.put("packageName", packageName);
        return getThis();
    }

    public T withCustomField(String customFieldId) {
        return addId(CUSTOM_FIELDS, customFieldId);
    }

    public T withCustomFields(List<String> customFieldIds) {
        return addIds(CUSTOM_FIELDS, customFieldIds);
    }

    public T withTag(String tagId) {
        return addId(TAGS, tagId);
    }

    public T withTags(List<String> tagIds) {
        return addIds(TAGS, tagIds);
    }

    @NotNull
    private T addId(String parameterName, String parameterValue) {
        Set<String> existingIds = getOrCreateParameter(parameterName);
        existingIds.add(parameterValue);
        return getThis();
    }

    @NotNull
    private T addIds(String parameter, List<String> tagIds) {
        Set<String> existingIds = getOrCreateParameter(parameter);
        existingIds.addAll(tagIds);
        return getThis();
    }

    @SuppressWarnings("unchecked")
    @NotNull
    private Set<String> getOrCreateParameter(String parameter) {
        Set<String> existingIds = new HashSet<>();
        String parameterId = parameter.concat(".").concat(IDS);
        if (parameters.containsKey(parameter)) {
            existingIds = (Set<String>) parameters.get(parameterId);
        } else {
            parameters.put(parameterId, existingIds);
        }
        return existingIds;
    }
}
