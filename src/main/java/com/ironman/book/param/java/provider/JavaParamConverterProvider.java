package com.ironman.book.param.java.provider;

import com.ironman.book.param.core.BaseParamConverterProvider;
import com.ironman.book.param.java.JavaParamConverterRegistry;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@RequiredArgsConstructor
@Provider
public class JavaParamConverterProvider extends BaseParamConverterProvider implements ParamConverterProvider {

    private final JavaParamConverterRegistry registry;

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (!registry.supports(rawType)) {
            return null;
        }

        String fieldName = extractFieldName(annotations);
        return registry.getConverter(rawType, fieldName);
    }
}
