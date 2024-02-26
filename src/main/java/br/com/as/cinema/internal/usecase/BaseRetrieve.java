package br.com.as.cinema.internal.usecase;


import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseRetrieve<T extends BaseEntity> {

    T execute(T entity);

    T execute(T entity, String expand);

    Optional<T> execute(String uuid);

    Optional<T> execute(String uuid, String expand);

    Page<T> execute(Pageable pageable, SearchCriteria searchCriteria);

}
