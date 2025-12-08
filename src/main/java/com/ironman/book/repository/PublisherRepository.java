package com.ironman.book.repository;

import com.ironman.book.entity.Publisher;
import com.ironman.book.util.StatusEnum;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ironman.book.util.ApplicationUtil.isNotBlank;

@ApplicationScoped
public class PublisherRepository implements PanacheRepositoryBase<Publisher, Integer> {
    public List<Publisher> findAllEnabledOrderByIdDesc() {
        Parameters status = Parameters.with("status", StatusEnum.ENABLED.getValue());
        return find("status = :status order by id desc", status)
                .list();
    }

    public List<Publisher> findAllByNameOrderByNameDesc(String publisherName) {
        List<String> predicates = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        if (isNotBlank(publisherName)) {
            predicates.add("upper(publisherName) like :publisherName");
            params.put("publisherName", "%" + publisherName.trim().toUpperCase() + "%");
        }

        // Always filter by enabled status
        predicates.add("status = :status");
        params.put("status", StatusEnum.ENABLED.getValue());

        String whereClause = String.join(" and ", predicates);
        String query = whereClause + " order by publisherName desc";

        return find(query, params).list();
    }

    public PanacheQuery<Publisher> searchEnabledPublishers(
            String publisherName,
            LocalDateTime createdAtStart,
            LocalDateTime createdAtEnd,
            Page page
    ) {
        Map<String, Object> params = new HashMap<>();
        List<String> predicates = new ArrayList<>();

        if (isNotBlank(publisherName)) {
            predicates.add("upper(publisherName) like :publisherName");
            params.put("publisherName", "%" + publisherName.trim().toUpperCase() + "%");
        }

        if (createdAtStart != null) {
            predicates.add("createdAt >= :createdAtStart");
            params.put("createdAtStart", createdAtStart);
        }

        if (createdAtEnd != null) {
            predicates.add("createdAt <= :createdAtEnd");
            params.put("createdAtEnd", createdAtEnd);
        }


        // Always filter by enabled status
        predicates.add("status = :status");
        params.put("status", StatusEnum.ENABLED.getValue());

        String whereClause = String.join(" and ", predicates);
        String query = whereClause + " order by id desc";

        return find(query, params).page(page);
    }

    public PanacheQuery<Publisher> searchPageAndSort(
            String publisherName,
            LocalDateTime createdAtStart,
            LocalDateTime createdAtEnd,
            Page page,
            Sort sort
    ) {
        Map<String, Object> params = new HashMap<>();
        List<String> predicates = new ArrayList<>();

        if (isNotBlank(publisherName)) {
            predicates.add("upper(publisherName) like :publisherName");
            params.put("publisherName", "%" + publisherName.trim().toUpperCase() + "%");
        }

        if (createdAtStart != null) {
            predicates.add("createdAt >= :createdAtStart");
            params.put("createdAtStart", createdAtStart);
        }

        if (createdAtEnd != null) {
            predicates.add("createdAt <= :createdAtEnd");
            params.put("createdAtEnd", createdAtEnd);
        }


        // Always filter by enabled status
        predicates.add("status = :status");
        params.put("status", StatusEnum.ENABLED.getValue());

        String whereClause = String.join(" and ", predicates);

        return find(whereClause, sort, params).page(page);
    }

}
