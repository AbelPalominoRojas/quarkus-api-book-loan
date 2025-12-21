package com.ironman.book.param.java;

import com.ironman.book.param.core.ParamConverterRegistry;
import com.ironman.book.param.java.converter.IntegerParamConverter;
import com.ironman.book.param.java.converter.LocalDateParamConverter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.ParamConverter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@ApplicationScoped
public class JavaParamConverterRegistry implements ParamConverterRegistry {
    private static final Map<Class<?>, Function<String, ParamConverter<?>>> CONVERTERS = new HashMap<>();

    static {
        CONVERTERS.put(LocalDate.class, LocalDateParamConverter::new);
        CONVERTERS.put(Integer.class, IntegerParamConverter::new);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> rawType, String fieldName) {
        var converterSupplier = CONVERTERS.get(rawType);

        if (converterSupplier == null) {
            return null;
        }

        return (ParamConverter<T>) converterSupplier.apply(fieldName);
    }

    @Override
    public boolean supports(Class<?> rawType) {
        return CONVERTERS.containsKey(rawType);
    }
}
