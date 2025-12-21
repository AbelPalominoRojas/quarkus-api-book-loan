package com.ironman.book.param.core;

import com.ironman.book.exception.ExceptionResponse;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ParamConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.ironman.book.exception.ExceptionConstant.MESSAGE_INVALID_INPUT_DATA;
import static com.ironman.book.exception.ExceptionResponse.ExceptionDetailResponse;
import static jakarta.ws.rs.core.Response.Status;

@Slf4j
public abstract class BaseParamConverter<T> implements ParamConverter<T> {
    protected final String fieldName;

    protected BaseParamConverter(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public T fromString(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return parse(value);
        } catch (Exception e) {
            log.error("Failed to convert parameter '{}' with value '{}'", fieldName, value, e);
            throw buildException();
        }
    }

    @Override
    public String toString(T value) {
        if (value == null) {
            return null;
        }

        return convertToString(value);
    }

    protected WebApplicationException buildException() {
        var detail = ExceptionDetailResponse.builder()
                .component(fieldName)
                .message(getErrorMessage())
                .build();

        var exceptionResponse = ExceptionResponse.builder()
                .message(MESSAGE_INVALID_INPUT_DATA)
                .details(List.of(detail))
                .build();

        var response = Response
                .status(Status.BAD_REQUEST)
                .entity(exceptionResponse)
                .build();

        return new WebApplicationException(response);
    }

    protected abstract T parse(String value) throws Exception;

    protected abstract String getErrorMessage();

    protected String convertToString(T value) {
        return value.toString();
    }
}
