package com.ironman.book.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum PublisherSortField {
    ID("id", "id"),
    PUBLISHER_NAME("name", "publisher_name"),
    CREATED_AT("createdAt", "created_at");

    private final String fieldName;
    private final String columnName;

    public static String getColumnName(String value) {
        return Arrays.stream(values())
                .filter(e -> e.getFieldName().equals(value))
                .findFirst()
                .map(PublisherSortField::getColumnName)
                .orElse(ID.getColumnName());
    }

    public static String getFieldName(String value) {
        return Arrays.stream(values())
                .filter(e -> e.getFieldName().equals(value))
                .findFirst()
                .map(PublisherSortField::getFieldName)
                .orElse(ID.getFieldName());
    }
}
