package com.ironman.book.dto.publisher;

import com.ironman.book.dto.common.PageSortRequest;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PublisherPageSortFilterQuery extends PageSortRequest implements Serializable {

    @QueryParam("name")
    private String name;

    @QueryParam("createdAtStart")
    private LocalDate createdAtStart;

    @QueryParam("createdAtEnd")
    private LocalDate createdAtEnd;
}
