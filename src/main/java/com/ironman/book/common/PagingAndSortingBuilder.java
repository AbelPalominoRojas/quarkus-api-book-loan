package com.ironman.book.common;

import com.ironman.book.dto.common.PageRequest;
import com.ironman.book.dto.common.PageResponse;
import com.ironman.book.dto.common.SortDirection;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import java.util.Optional;
import java.util.function.Function;

import static io.quarkus.panache.common.Sort.Direction;

public abstract class PagingAndSortingBuilder {
    public <T, U> PageResponse<U> buildPageResponse(
            PageRequest page,
            PanacheQuery<T> query,
            Function<T, U> converter
    ) {
        var content = query.list()
                .stream()
                .map(converter)
                .toList();
        long totalElements = query.count();
        int numberOfElements = content.size();
        int totalPages = getTotalPages(page.getPageSize(), totalElements);

        return PageResponse.<U>builder()
                .content(content)
                .pageNumber(page.getPageNumber())
                .pageSize(page.getPageSize())
                .numberOfElements(numberOfElements)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }

    private static int getTotalPages(int pageSize, long totalElements) {
        return pageSize > 0
                ? (int) Math.ceil(totalElements / (double) pageSize)
                : 0;
    }

    public Page buildPage(PageRequest page) {
        return Page.of(page.getPageNumber() - 1, page.getPageSize());
    }

    public Direction buildDirection(SortDirection sortOrder) {
        return Optional.ofNullable(sortOrder)
                .filter(order -> order == SortDirection.ASC)
                .map(order -> Direction.Ascending)
                .orElse(Direction.Descending);
    }
}
