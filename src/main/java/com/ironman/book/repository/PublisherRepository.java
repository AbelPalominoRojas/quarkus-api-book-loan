package com.ironman.book.repository;

import com.ironman.book.entity.Publisher;
import com.ironman.book.util.StatusEnum;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PublisherRepository implements PanacheRepositoryBase<Publisher, Integer> {
    public List<Publisher> findAllEnabledOrderByIdDesc() {
        Parameters status = Parameters.with("status", StatusEnum.ENABLED.getValue());
        return find("status = :status order by id desc", status)
                .list();
    }
}
