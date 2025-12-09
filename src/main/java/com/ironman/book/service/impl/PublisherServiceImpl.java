package com.ironman.book.service.impl;

import com.ironman.book.common.PagingAndSortingBuilder;
import com.ironman.book.dto.common.PageResponse;
import com.ironman.book.dto.common.SortDirection;
import com.ironman.book.dto.publisher.*;
import com.ironman.book.entity.Publisher;
import com.ironman.book.entity.enums.PublisherSortField;
import com.ironman.book.mapper.PublisherMapper;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.service.PublisherService;
import com.ironman.book.util.StatusEnum;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.ironman.book.exception.ExceptionCatalog.PUBLISHER_NOT_FOUND;
import static io.quarkus.panache.common.Sort.Direction;

@RequiredArgsConstructor
@ApplicationScoped
public class PublisherServiceImpl extends PagingAndSortingBuilder implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public List<PublisherBriefResponse> findAll() {
        return publisherRepository.findAllEnabledOrderByIdDesc()
                .stream()
                .map(publisherMapper::toSummaryResponse)
                .toList();
    }

    @Override
    public PublisherDetailResponse findById(Integer id) {
        var publisher = getPublisherOrThrow(id);

        return publisherMapper.toDetailResponse(publisher);
    }

    @Transactional
    @Override
    public PublisherResponse create(PublisherRequest publisherRequest) {
        var publisher = publisherMapper.toEntity(publisherRequest);

        publisherRepository.persist(publisher);

        return publisherMapper.toResponse(publisher);
    }

    @Transactional
    @Override
    public PublisherResponse update(Integer id, PublisherRequest publisherRequest) {
        var publisher = getPublisherOrThrow(id);

        publisherMapper.updateEntity(publisher, publisherRequest);

        return publisherMapper.toResponse(publisher);
    }

    @Transactional
    @Override
    public PublisherResponse disableById(Integer id) {
        var publisher = getPublisherOrThrow(id);
        publisher.setStatus(StatusEnum.DISABLED.getValue());

        publisherRepository.persist(publisher);

        return publisherMapper.toResponse(publisher);
    }

    @Override
    public List<PublisherCompactResponse> findAllByName(String name) {
        return publisherRepository.findAllByNameOrderByNameDesc(name)
                .stream()
                .map(publisherMapper::toCompactResponse)
                .toList();
    }

    @Override
    public PageResponse<PublisherOverviewResponse> searchAndPaginate(PublisherPageFilterQuery filterQuery) {
        LocalDateTime createdAtStart = null;
        LocalDateTime createdAtEnd = null;

        if (filterQuery.getCreatedAtStart() != null) {
            createdAtStart = filterQuery.getCreatedAtStart().atStartOfDay();
        }

        if (filterQuery.getCreatedAtEnd() != null) {
            createdAtEnd = filterQuery.getCreatedAtEnd().atTime(23, 59, 59);
        }

        var page = buildPage(filterQuery);

        var panacheQuery = publisherRepository.searchEnabledPublishers(
                filterQuery.getName(),
                createdAtStart,
                createdAtEnd,
                page
        );

        return buildPageResponse(filterQuery, panacheQuery, publisherMapper::toOverviewResponse);
    }

    @Override
    public PageResponse<PublisherOverviewResponse> searchSortPaginate(PublisherPageSortFilterQuery filterQuery) {
        LocalDateTime createdAtStart = null;
        LocalDateTime createdAtEnd = null;

        if (filterQuery.getCreatedAtStart() != null) {
            createdAtStart = filterQuery.getCreatedAtStart().atStartOfDay();
        }

        if (filterQuery.getCreatedAtEnd() != null) {
            createdAtEnd = filterQuery.getCreatedAtEnd().atTime(23, 59, 59);
        }

        var page = buildPage(filterQuery);

        Direction direction = buildDirection(filterQuery.getSortOrder());

        String propertyName = Optional.ofNullable(filterQuery.getSortField())
                .map(PublisherSortOptionField::getPropertyName)
                .orElse(null);

        String fieldName = PublisherSortField.getFieldName(propertyName);
        Sort sort = Sort.by(fieldName, direction);

        var panacheQuery = publisherRepository.searchPageAndSort(
                filterQuery.getName(),
                createdAtStart,
                createdAtEnd,
                page,
                sort
        );

        return buildPageResponse(filterQuery, panacheQuery, publisherMapper::toOverviewResponse);
    }

    @Override
    public PageResponse<PublisherOverviewResponse> projectionSearchPageAndSort(PublisherPageSortFilterQuery filterQuery) {
        LocalDateTime createdAtStart = null;
        LocalDateTime createdAtEnd = null;

        if (filterQuery.getCreatedAtStart() != null) {
            createdAtStart = filterQuery.getCreatedAtStart().atStartOfDay();
        }

        if (filterQuery.getCreatedAtEnd() != null) {
            createdAtEnd = filterQuery.getCreatedAtEnd().atTime(23, 59, 59);
        }

        var page = buildPage(filterQuery);

        Direction direction = buildDirection(filterQuery.getSortOrder());

        String propertyName = Optional.ofNullable(filterQuery.getSortField())
                .map(PublisherSortOptionField::getPropertyName)
                .orElse(null);

        String fieldName = PublisherSortField.getFieldName(propertyName);
        Sort sort = Sort.by(fieldName, direction);

        var panacheQuery = publisherRepository.projectionSearchPageAndSort(
                filterQuery.getName(),
                createdAtStart,
                createdAtEnd,
                page,
                sort
        );

        return buildPageResponse(filterQuery, panacheQuery, publisherMapper::toOverviewResponse);
    }

    private Publisher getPublisherOrThrow(Integer id) {
        return publisherRepository.findByIdOptional(id)
                .orElseThrow(() -> PUBLISHER_NOT_FOUND.buildException(id));
    }
}
