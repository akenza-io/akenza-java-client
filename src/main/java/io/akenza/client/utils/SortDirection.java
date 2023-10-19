package io.akenza.client.utils;

public enum SortDirection {
    ASC("asc"),
    DESC("desc");

    SortDirection(String value) {
        this.value = value;
    }

    private final String value;

    public String getValue() {
        return value;
    }
}
