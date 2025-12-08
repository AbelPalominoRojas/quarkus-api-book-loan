package com.ironman.book.dto.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortDirection {
    ASC("asc"),
    DESC("desc");

    private final String direction;

}
