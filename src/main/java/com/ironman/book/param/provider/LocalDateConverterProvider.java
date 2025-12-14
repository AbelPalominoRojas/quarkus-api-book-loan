package com.ironman.book.param.provider;

import com.ironman.book.param.converter.LocalDateConverter;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;

@Provider
public class LocalDateConverterProvider implements ParamConverterProvider {

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (!rawType.equals(LocalDate.class)) {
            return null;
        }

        String fieldName = extractFieldName(annotations);
        return (ParamConverter<T>) new LocalDateConverter(fieldName);
    }

    private String extractFieldName(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(QueryParam.class::isInstance)
                .map(QueryParam.class::cast)
                .map(QueryParam::value)
                .findFirst()
                .orElse("unknown");
    }
}
