package com.ironman.book.dto.publisher;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherCompactResponse implements Serializable {
    private Integer id;
    private String code;
    private String name;
}
