package com.ironman.book.dto.publisher;

import com.ironman.book.dto.common.RecordStatusResponse;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherOverviewResponse implements Serializable {
    private Integer id;
    private String code;
    private String name;
    private RecordStatusResponse status;
}
