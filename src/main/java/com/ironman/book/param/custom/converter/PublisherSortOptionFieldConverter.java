package com.ironman.book.param.custom.converter;

import com.ironman.book.dto.publisher.PublisherSortOptionField;
import com.ironman.book.param.core.BaseParamConverter;

import java.util.Arrays;

public class PublisherSortOptionFieldConverter extends BaseParamConverter<PublisherSortOptionField> {
    private static final String ERROR_MESSAGE = "Invalid sort field. Allowed values: id, name, createdAt.";

    public PublisherSortOptionFieldConverter(String fieldName) {
        super(fieldName);
    }

    @Override
    protected PublisherSortOptionField parse(String value) {
        return Arrays.stream(PublisherSortOptionField.values())
                .filter(option -> option.getPropertyName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE));
    }

    @Override
    protected String getErrorMessage() {
        return ERROR_MESSAGE;
    }

    @Override
    protected String convertToString(PublisherSortOptionField value) {
        return value.getPropertyName();
    }
}
