package io.akenza.client.utils;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface Page<T> {
    Integer totalElements();

    Integer size();

    Boolean last();

    List<T> content();
}
