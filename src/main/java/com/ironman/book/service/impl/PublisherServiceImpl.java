package com.ironman.book.service.impl;

import com.ironman.book.mapper.PublisherMapper;
import com.ironman.book.dto.publisher.PublisherDetailResponse;
import com.ironman.book.dto.publisher.PublisherRequest;
import com.ironman.book.dto.publisher.PublisherResponse;
import com.ironman.book.dto.publisher.PublisherSummaryResponse;
import com.ironman.book.entity.Publisher;
import com.ironman.book.repository.PublisherRepository;
import com.ironman.book.service.PublisherService;
import com.ironman.book.util.StatusEnum;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@ApplicationScoped
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public List<PublisherSummaryResponse> findAll() {
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

    private Publisher getPublisherOrThrow(Integer id) {
        return publisherRepository.findByIdOptional(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id: " + id));
    }
}
