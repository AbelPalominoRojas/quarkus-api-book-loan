package com.ironman.book.param.custom;

import com.ironman.book.dto.common.SortDirection;
import com.ironman.book.dto.publisher.PublisherSortOptionField;
import com.ironman.book.param.core.ParamConverterRegistry;
import com.ironman.book.param.custom.converter.PublisherSortOptionFieldConverter;
import com.ironman.book.param.custom.converter.SortDirectionConverter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.ParamConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@ApplicationScoped
public class CustomParamConverterRegistry implements ParamConverterRegistry {
    private static final Map<Class<?>, Function<String, ParamConverter<?>>> CONVERTERS = new HashMap<>();

    static {
        CONVERTERS.put(SortDirection.class, SortDirectionConverter::new);
        CONVERTERS.put(PublisherSortOptionField.class, PublisherSortOptionFieldConverter::new);
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
