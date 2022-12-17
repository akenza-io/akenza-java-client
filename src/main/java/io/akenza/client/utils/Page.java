package io.akenza.client.utils;

import java.util.List;

public interface Page<T> {
    Integer totalElements();

    Integer size();

    Boolean last();

    List<T> content();
}
