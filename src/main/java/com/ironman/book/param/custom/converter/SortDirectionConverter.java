package com.ironman.book.param.custom.converter;

import com.ironman.book.dto.common.SortDirection;
import com.ironman.book.param.core.BaseParamConverter;

import java.util.Arrays;

public class SortDirectionConverter extends BaseParamConverter<SortDirection> {
    private static final String ERROR_MESSAGE = "Invalid sort direction. Allowed values: asc, desc.";

    public SortDirectionConverter(String fieldName) {
        super(fieldName);
    }

    @Override
    protected SortDirection parse(String value) {
        return Arrays.stream(SortDirection.values())
                .filter(option -> option.getDirection().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    @Override
    protected String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    @Override
    protected String convertToString(SortDirection value) {
        return value.getDirection();
    }
}
