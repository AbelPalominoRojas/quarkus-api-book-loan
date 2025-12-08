package com.ironman.book.dto.publisher;

import com.ironman.book.dto.common.PageRequest;
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
public class PublisherPageFilterQuery extends PageRequest implements Serializable {

    @QueryParam("name")
    private String name;

    @QueryParam("createdAtStart")
    private LocalDate createdAtStart;

    @QueryParam("createdAtEnd")
    private LocalDate createdAtEnd;
}
