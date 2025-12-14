package com.ironman.book.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionConstant {
    public static final String MESSAGE_INVALID_INPUT_DATA = "Invalid input data. Verify format and values.";
    public static final String MESSAGE_RESOURCE_NOT_FOUND = "Resource not found. Verify the identifier.";
    public static final String MESSAGE_INTERNAL_ERROR = "Internal server error. Contact administrator.";
    public static final String MESSAGE_UNEXPECTED_ERROR = "Unexpected error. Please try again later.";

    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format. Please use YYYY-MM-DD format.";


    public static final String COMPONENT_PUBLISHER_SERVICE = "Publisher Service";
    public static final String COMPONENT_DATABASE_SERVICE = "Database Service";
}
