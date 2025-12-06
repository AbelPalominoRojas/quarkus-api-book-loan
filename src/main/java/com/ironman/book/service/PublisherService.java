package com.ironman.book.service;

import com.ironman.book.dto.publisher.PublisherDetailResponse;
import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.dto.publisher.PublisherResponse;
import com.ironman.book.dto.publisher.PublisherSummaryResponse;

import java.util.List;

public interface PublisherService {

    List<PublisherSummaryResponse> findAll();

    PublisherDetailResponse findById(Integer id);

    PublisherResponse create(PublisherRequest publisherRequest);

    PublisherResponse update(Integer id, PublisherRequest publisherRequest);

    PublisherResponse disableById(Integer id);
}
