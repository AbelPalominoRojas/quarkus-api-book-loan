package com.ironman.book.param.converter;

import com.ironman.book.exception.ExceptionResponse;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ParamConverter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.ironman.book.exception.ExceptionConstant.MESSAGE_INVALID_DATE_FORMAT;
import static com.ironman.book.exception.ExceptionConstant.MESSAGE_INVALID_INPUT_DATA;
import static com.ironman.book.exception.ExceptionResponse.ExceptionDetailResponse;

@RequiredArgsConstructor
public class LocalDateConverter implements ParamConverter<LocalDate> {
    private final String fieldName;

    @Override
    public LocalDate fromString(String value) {
        if (value == null || value.isBlank()) return null;

        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException e) {

            var detail = ExceptionDetailResponse.builder()
                    .component(fieldName)
                    .message(MESSAGE_INVALID_DATE_FORMAT)
                    .build();

            var exceptionResponse = ExceptionResponse.builder()
                    .message(MESSAGE_INVALID_INPUT_DATA)
                    .details(List.of(detail))
                    .build();

            var response = Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(exceptionResponse)
                    .build();

            throw new WebApplicationException(response);
        }
    }

    @Override
    public String toString(LocalDate value) {
        if (value == null) return null;

        return value.toString();
    }
}
