package com.ironman.book.param.java.converter;

import com.ironman.book.param.core.BaseParamConverter;

public class IntegerParamConverter extends BaseParamConverter<Integer> {
    private static final String ERROR_MESSAGE = "Invalid integer format.";

    public IntegerParamConverter(String fieldName) {
        super(fieldName);
    }

    @Override
    protected Integer parse(String value) {
        return Integer.valueOf(value);
    }

    @Override
    protected String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
