package com.ironman.book.controller.exception;

import com.ironman.book.exception.ApplicationException;
import com.ironman.book.exception.ExceptionResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

import static com.ironman.book.exception.ExceptionConstant.*;
import static com.ironman.book.exception.ExceptionResponse.ExceptionDetailResponse;
import static com.ironman.book.exception.ApplicationException.ExceptionType;
import static jakarta.ws.rs.core.Response.Status;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
    @Override
    public Response toResponse(ApplicationException exception) {
        var detail = ExceptionDetailResponse.builder()
                .component(exception.getComponent())
                .message(exception.getMessage())
                .build();

        String message = messageFromExceptionType(exception.getExceptionType());
        ExceptionResponse response = createExceptionResponse(message, List.of(detail));
        Status status = mapToJaxRsExceptionType(exception.getExceptionType());

        return Response
                .status(status)
                .entity(response)
                .build();
    }

    private ExceptionResponse createExceptionResponse(String message, List<ExceptionDetailResponse> details) {
        return ExceptionResponse.builder()
                .message(message)
                .details(details)
                .build();
    }

    private String messageFromExceptionType(ExceptionType exceptionType) {
        return switch (exceptionType) {
            case BAD_REQUEST -> MESSAGE_INVALID_INPUT_DATA;
            case NOT_FOUND -> MESSAGE_RESOURCE_NOT_FOUND;
            case INTERNAL_SERVER_ERROR -> MESSAGE_INTERNAL_ERROR;
            default -> MESSAGE_UNEXPECTED_ERROR;
        };
    }

    private Status mapToJaxRsExceptionType(ExceptionType exceptionType) {
        return switch (exceptionType) {
            case BAD_REQUEST -> Status.BAD_REQUEST;
            case NOT_FOUND -> Status.NOT_FOUND;
            default -> Status.INTERNAL_SERVER_ERROR;
        };
    }
}
