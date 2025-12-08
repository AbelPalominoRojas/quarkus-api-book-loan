package com.ironman.book.dto.common;

import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PageSortRequest extends PageRequest implements Serializable {

    @QueryParam("sortField")
    private String sortField;

    @QueryParam("sortOrder")
    private SortDirection sortOrder;
}
