package com.ironman.book.dto.common;

import jakarta.validation.constraints.Min;
import jakarta.ws.rs.DefaultValue;
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
@SuperBuilder(builderMethodName = "pageRequestBuilder")
public class PageRequest implements Serializable {
    @QueryParam("pageNumber")
    @DefaultValue("1")
    @Min(1)
    private Integer pageNumber;

    @QueryParam("pageSize")
    @DefaultValue("10")
    @Min(1)
    private Integer pageSize;
}
