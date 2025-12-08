package com.ironman.book.dto.publisher;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PublisherSortOptionField {
    ID("id"),
    NAME("name"),
    CREATED_AT("createdAt");

    private final String propertyName;
}
