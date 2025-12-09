package com.ironman.book.entity.projection;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@RegisterForReflection
public class PublisherProjection {
    private Integer id;
    private String publisherCode;
    private String publisherName;
    private LocalDateTime createdAt;
}
