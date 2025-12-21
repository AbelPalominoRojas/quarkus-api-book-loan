package com.ironman.book.mapper;

import com.ironman.book.dto.publisher.*;
import com.ironman.book.entity.Publisher;
import com.ironman.book.entity.projection.PublisherProjection;
import com.ironman.book.util.StatusEnum;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel;

@Mapper(
        componentModel = ComponentModel.JAKARTA_CDI,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        imports = {StatusEnum.class},
        uses = {MapRecordStatus.class}
)
public interface PublisherMapper {

    @Mapping(target = "code", source = "publisherCode")
    @Mapping(target = "name", source = "publisherName")
    @Mapping(target = "status", source = "status")
    PublisherDetailResponse toDetailResponse(Publisher publisher);

    @Mapping(target = "code", source = "publisherCode")
    @Mapping(target = "name", source = "publisherName")
    PublisherOverviewResponse toOverviewResponse(Publisher publisher);

    @Mapping(target = "code", source = "publisherCode")
    @Mapping(target = "name", source = "publisherName")
    PublisherCompactResponse toCompactResponse(Publisher publisher);

    @Mapping(target = "name", source = "publisherName")
    PublisherBriefResponse toSummaryResponse(Publisher publisher);

    @Mapping(target = "name", source = "publisherName")
    @Mapping(target = "status", source = "status")
    PublisherResponse toResponse(Publisher publisher);

    @Mapping(target = "code", source = "publisherCode")
    @Mapping(target = "name", source = "publisherName")
    PublisherOverviewResponse toOverviewResponse(PublisherProjection publisher);

    @Mapping(target = "publisherCode", source = "code")
    @Mapping(target = "publisherName", source = "name")
    @Mapping(target = "status", expression = "java(StatusEnum.ENABLED.getValue())")
    Publisher toEntity(PublisherRequest publisherRequest);

    @Mapping(target = "publisherCode", source = "code")
    @Mapping(target = "publisherName", source = "name")
    void updateEntity(@MappingTarget Publisher publisher, PublisherRequest publisherRequest);
}
