package com.ironman.book.service;

import com.ironman.book.dto.common.PageResponse;
import com.ironman.book.dto.publisher.*;

import java.util.List;

public interface PublisherService {

    List<PublisherBriefResponse> findAll();

    PublisherDetailResponse findById(Integer id);

    PublisherResponse create(PublisherRequest publisherRequest);

    PublisherResponse update(Integer id, PublisherRequest publisherRequest);

    PublisherResponse disableById(Integer id);

    List<PublisherCompactResponse> findAllByName(String name);

    PageResponse<PublisherOverviewResponse> searchAndPaginate(PublisherPageFilterQuery filterQuery);
}
