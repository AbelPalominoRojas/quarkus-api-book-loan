package com.ironman.book.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationUtil {

    public static boolean isNotBlank(String value) {
        return value != null && !value.isBlank();
    }
}
