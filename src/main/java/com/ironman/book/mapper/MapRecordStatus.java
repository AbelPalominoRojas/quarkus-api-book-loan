package com.ironman.book.mapper;

import com.ironman.book.config.RecordStatusConfig;
import com.ironman.book.dto.common.RecordStatusResponse;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class MapRecordStatus {
    private final RecordStatusConfig recordStatusConfig;

    public RecordStatusResponse mappingStatus(Integer status) {
        return recordStatusConfig.statuses().stream()
                .filter(s -> s.id().equals(status))
                .findFirst()
                .map(this::buildRecordStatusResponse)
                .orElse(null);
    }

    private RecordStatusResponse buildRecordStatusResponse(RecordStatusConfig.StatusData statusData) {
        return RecordStatusResponse.builder()
                .id(statusData.id())
                .name(statusData.name())
                .build();
    }
}
