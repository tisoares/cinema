package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.BaseEntity;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.BaseRetrieve;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.ParameterizedType;
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
    public Page<T> execute(Pageable pageable, SearchCriteria searchCriteria) {
        return repository.findAll(entitySpecification.specificationBuilder(searchCriteria), pageable);
    }


    @Override
    public T execute(T entity) {
        Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];

        if (Objects.isNull(entity)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity %s not found", clazz.getSimpleName()));
        }
        Optional<T> saved = execute(entity.getUuid());
        if (saved.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Entity %s %s not found", clazz.getSimpleName(), entity.getUuid()));
        }
        return saved.get();
    }
}