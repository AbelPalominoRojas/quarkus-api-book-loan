package com.ironman.book.controller.exception;

import com.ironman.book.exception.ExceptionResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.ironman.book.exception.ExceptionConstant.MESSAGE_INVALID_INPUT_DATA;
import static com.ironman.book.exception.ExceptionResponse.ExceptionDetailResponse;
import static jakarta.ws.rs.core.Response.Status;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var details = exception.getConstraintViolations()
                .stream()
                .map(violation -> ExceptionDetailResponse.builder()
                        .component(getFieldName(violation))
                        .message(violation.getMessage())
                        .build())
                .toList();

        var response = ExceptionResponse.builder()
                .message(MESSAGE_INVALID_INPUT_DATA)
                .details(details)
                .build();

        return Response
                .status(Status.BAD_REQUEST)
                .entity(response)
                .build();
    }

    @RequiredArgsConstructor
    @Getter
    private enum SkipRule {
        NONE(0),
        TWO_NODES(1),
        THREE_OR_MORE_NODES(2);

        private final int skipCount;

        public static SkipRule fromNodeCount(int nodeCount) {
            if (nodeCount >= 3) return THREE_OR_MORE_NODES;
            if (nodeCount == 2) return TWO_NODES;
            return NONE;
        }
    }

    private String getFieldName(ConstraintViolation<?> violation) {
        var nodeNames = StreamSupport
                .stream(violation.getPropertyPath().spliterator(), false)
                .map(Path.Node::getName)
                .filter(name -> name != null && !name.isEmpty())
                .toList();
        int skipCount = SkipRule
                .fromNodeCount(nodeNames.size())
                .getSkipCount();


        return nodeNames.stream()
                .skip(skipCount)
                .collect(Collectors.joining("."));
    }
}
