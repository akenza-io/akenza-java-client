package io.akenza.client.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.akenza.client.exceptions.DeserializationException;

import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * Json class to facilitate reading and writing json.
 */
public class Json {

    private final ObjectMapper mapper;

    /**
     * Constructor not to be used in a factory class.
     */
    private Json(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Create a pre-configured ObjectMapper object.
     *
     * @return ObjectMapper object
     */
    public static Json create() {
        return withMapper(DefaultMapper.INSTANCE);
    }

    /**
     * Create an instance with a provided mapper.
     *
     * @param mapper objectmapper
     * @return Json
     */
    public static Json withMapper(final ObjectMapper mapper) {
        return new Json(mapper);
    }

    /**
     * Serialize an object to a json string. Use when you don't know if object is serializable.
     *
     * @param value The object to serialize.
     * @return The serialized object.
     * @see #toJson(Object)
     */
    public byte[] toJson(final Object value) throws JsonProcessingException {
        return mapper.writeValueAsBytes(value);
    }

    /**
     * Serialize an object to a json string. Use when object is expected to be json serializable.
     *
     * @param value The object to serialize.
     * @return The serialized object.
     * @see #toJson(Object)
     */
    public byte[] toJsonUnchecked(final Object value) {
        try {
            return toJson(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * De-serialize an object from a json string. Use when you don't know if object is serializable.
     *
     * @param content The string to de-serialize.
     * @param clazz   The object to de-serialize to.
     * @return The serialized object.
     */
    public <T> T fromJson(final String content, final Class<T> clazz) throws IOException {
        return mapper.readValue(content, clazz);
    }

    /**
     * De-serialize an object from a json string. Use when you don't know if object is serializable.
     *
     * @param content       The string to de-serialize.
     * @param typeReference The object to de-serialize to.
     * @return The serialized object.
     */
    public <T> T fromJson(final String content, final TypeReference<T> typeReference)
            throws IOException {
        return mapper.readValue(content, typeReference);
    }

    /**
     * De-serialize an object from a json string. Use when you don't know if object is serializable.
     *
     * @param content  The string to de-serialize.
     * @param javaType The object to de-serialize to.
     * @return The serialized object.
     */
    public <T> T fromJson(final String content, final JavaType javaType) throws IOException {
        return mapper.readValue(content, javaType);
    }

    /**
     * De-serialize an object from a json string. Use when you don't know if object is serializable.
     *
     * @param bytes The string to de-serialize.
     * @param clazz The object to de-serialize to.
     * @return The serialized object.
     */
    public <T> T fromJson(final byte[] bytes, final Class<T> clazz) throws IOException {
        return mapper.readValue(bytes, clazz);
    }

    /**
     * De-serialize an object from a json string. Use when you don't know if object is serializable.
     *
     * @param bytes         The string to de-serialize.
     * @param typeReference The object to de-serialize to.
     * @return The serialized object.
     */
    public <T> T fromJson(final byte[] bytes, final TypeReference<T> typeReference)
            throws IOException {
        return mapper.readValue(bytes, typeReference);
    }

    /**
     * De-serialize an object from a json string. Use when you don't know if object is serializable.
     *
     * @param bytes    The string to de-serialize.
     * @param javaType The object to de-serialize to.
     * @return The serialized object.
     */
    public <T> T fromJson(final byte[] bytes, final JavaType javaType) throws IOException {
        return mapper.readValue(bytes, javaType);
    }

    /**
     * De-serialize an object from a json string. Use when know the object is serializable.
     *
     * @param content The string to de-serialize.
     * @param clazz   The object to de-serialize to.
     * @return The serialized object.
     * @see #fromJsonUnchecked(String, Class)
     */
    public <T> T fromJsonUnchecked(final String content, final Class<T> clazz) {
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * De-serialize an object from a json string. Use when know the object is serializable.
     *
     * @param content       The string to de-serialize.
     * @param typeReference The object to de-serialize to.
     * @return The serialized object.
     * @see #fromJson(String, TypeReference)
     */
    public <T> T fromJsonUnchecked(final String content, final TypeReference<T> typeReference) {
        try {
            return mapper.readValue(content, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * De-serialize an object from a json string. Use when know the object is serializable.
     *
     * @param content The string to de-serialize.
     * @param clazz   The object to de-serialize to.
     * @return The serialized object.
     * @see #fromJsonUnchecked(String, Class)
     */
    @SuppressWarnings("Duplicates")
    public <T> T fromJsonUncheckedNotNull(final String content, final Class<T> clazz) {
        try {
            final T t = mapper.readValue(content, clazz);
            if (isNull(t)) {
                throw new DeserializationException(content);
            }
            return t;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * De-serialize an object from a json string. Use when know the object is serializable.
     *
     * @param content       The string to de-serialize.
     * @param typeReference The object to de-serialize to.
     * @return The serialized object.
     * @see #fromJson(String, TypeReference)
     */
    @SuppressWarnings("Duplicates")
    public <T> T fromJsonUncheckedNotNull(
            final String content, final TypeReference<T> typeReference) {
        try {
            final T t = mapper.readValue(content, typeReference);
            if (isNull(t)) {
                throw new DeserializationException(content);
            }
            return t;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    private static class DefaultMapper {

        private static final ObjectMapper INSTANCE =
                new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule())
                        .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
                        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                        .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
                        .enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }
}
