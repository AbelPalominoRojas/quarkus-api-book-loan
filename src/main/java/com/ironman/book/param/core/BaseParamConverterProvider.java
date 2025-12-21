package com.ironman.book.param.core;

import jakarta.ws.rs.QueryParam;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public abstract class BaseParamConverterProvider {

    protected String extractFieldName(Annotation[] annotations) {
        return Arrays.stream(annotations)
                .filter(QueryParam.class::isInstance)
                .map(QueryParam.class::cast)
                .map(QueryParam::value)
                .findFirst()
                .orElse("unknown");
    }
}
