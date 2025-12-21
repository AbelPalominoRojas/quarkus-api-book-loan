package com.ironman.book.param.core;

import jakarta.ws.rs.ext.ParamConverter;

public interface ParamConverterRegistry {

    <T> ParamConverter<T> getConverter(Class<T> rawType, String fieldName);

    boolean supports(Class<?> rawType);
}
