package com.ironman.book.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.ironman.book.exception.ApplicationException.ExceptionType;
import static com.ironman.book.exception.ExceptionConstant.COMPONENT_DATABASE_SERVICE;
import static com.ironman.book.exception.ExceptionConstant.COMPONENT_PUBLISHER_SERVICE;

@Getter
@RequiredArgsConstructor
public enum ExceptionCatalog {
    DATABASE_UNEXPECTED_ERROR(
            ExceptionType.INTERNAL_SERVER_ERROR,
            COMPONENT_DATABASE_SERVICE,
            "An unexpected error occurred in the database service."
    ),
    UNEXPECTED_ERROR(
            ExceptionType.INTERNAL_SERVER_ERROR,
            "Application",
            "An unexpected error occurred, please try again later."
    ),
    PUBLISHER_NOT_FOUND(
            ExceptionType.NOT_FOUND,
            COMPONENT_PUBLISHER_SERVICE,
            "Publisher not found with id: %s"
    ),
    PUBLISHER_CODE_CONFLICT(
            ExceptionType.CONFLICT,
            COMPONENT_PUBLISHER_SERVICE,
            "Publisher code already exists: %s"
    );

    private final ExceptionType exceptionType;
    private final String component;
    private final String message;

    public ApplicationException buildException(Object... args) {
        String formattedMessage = String.format(message, args);

        return ApplicationException.builder()
                .exceptionType(exceptionType)
                .component(component)
                .message(formattedMessage)
                .build();
    }
}
