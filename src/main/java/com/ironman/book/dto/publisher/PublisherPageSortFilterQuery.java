package com.ironman.book.dto.publisher;

import com.ironman.book.dto.common.PageRequest;
import com.ironman.book.dto.common.SortDirection;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
public class PublisherPageSortFilterQuery extends PageRequest implements Serializable {

    @QueryParam("name")
    @Pattern(regexp = "^[A-Za-zñÑ0-9'´ .,-]+$", message = "Publisher name contains invalid characters")
    private String name;

    @QueryParam("createdAtStart")
    @PastOrPresent(message = "createdAtStart must be a past or present date")
    private LocalDate createdAtStart;

    @QueryParam("createdAtEnd")
    @PastOrPresent(message = "createdAtEnd must be a past or present date")
    private LocalDate createdAtEnd;

    @QueryParam("sortField")
    private PublisherSortOptionField sortField;

    @QueryParam("sortOrder")
    private SortDirection sortOrder;
}
