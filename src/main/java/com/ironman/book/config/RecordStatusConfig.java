package com.ironman.book.config;

import io.smallrye.config.ConfigMapping;

import java.util.List;

@ConfigMapping(prefix = "record-status")
public interface RecordStatusConfig {
    List<StatusData> statuses();

    interface StatusData {
        Integer id();

        String name();
    }
}
