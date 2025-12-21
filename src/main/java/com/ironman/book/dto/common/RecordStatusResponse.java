package com.ironman.book.dto.common;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordStatusResponse implements Serializable {
    private Integer id;
    private String name;
}
