package com.ironman.book.dto.publisher;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherBriefResponse implements Serializable {
    private Integer id;
    private String name;
}
