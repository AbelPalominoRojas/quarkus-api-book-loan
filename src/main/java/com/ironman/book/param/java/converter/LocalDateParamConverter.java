package com.ironman.book.param.java.converter;

import com.ironman.book.param.core.BaseParamConverter;

import java.time.LocalDate;

public class LocalDateParamConverter extends BaseParamConverter<LocalDate> {
    private static final String ERROR_MESSAGE = "Invalid date format. Please use YYYY-MM-DD format.";

    public LocalDateParamConverter(String fieldName) {
        super(fieldName);
    }

    @Override
    protected LocalDate parse(String value) {
        return LocalDate.parse(value);
    }

    @Override
    protected String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
