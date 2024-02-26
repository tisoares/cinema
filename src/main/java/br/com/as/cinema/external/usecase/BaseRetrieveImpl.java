package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.BaseEntity;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.BaseRetrieve;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
public abstract class BaseRetrieveImpl<T extends BaseEntity> implements BaseRetrieve<T> {

    protected final BaseRepository<T> repository;
    protected final EntitySpecification entitySpecification;

    @Override
    public Optional<T> execute(String uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    public Optional<T> execute(String uuid, String expand) {
        if (StringUtils.isBlank(expand)) {
            return this.execute(uuid);
        }
        return repository.findOne(entitySpecification.specificationBuilder(SearchCriteria.builder()
                .expands(Arrays.asList(expand.split(",")))
                .filters(List.of(SearchCriteria.Filter.builder()
                        .field("uuid")
                        .operator(SearchCriteria.Filter.QueryOperator.EQUALS)
                        .value(uuid)
                        .build()))
                .build()));
    }

    @Override
    public Page<T> execute(Pageable pageable, SearchCriteria searchCriteria) {
        return repository.findAll(entitySpecification.specificationBuilder(searchCriteria), pageable);
    }


    @Override
    public T execute(T entity, String extend) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        if (Objects.isNull(entity)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity %s not found", clazz.getSimpleName()));
        }
        Optional<T> saved = execute(entity.getUuid(), extend);
        if (saved.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity %s %s not found", clazz.getSimpleName(), entity.getUuid()));
        }
        return saved.get();
    }

    public T execute(T entity) {
        return this.execute(entity, "");
    }
}
