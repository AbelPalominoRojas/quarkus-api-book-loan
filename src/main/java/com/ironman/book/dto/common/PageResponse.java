package com.ironman.book.dto.common;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
public class PageResponse<T>  implements Serializable {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer numberOfElements;
    private Long totalElements;
    private int totalPages;
}
