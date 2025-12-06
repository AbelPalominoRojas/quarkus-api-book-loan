package com.ironman.book.dto.publisher;

import com.ironman.book.dto.common.RecordStatusResponse;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherResponse implements Serializable {

    private Integer id;
    private String name;
    private RecordStatusResponse status;
}
