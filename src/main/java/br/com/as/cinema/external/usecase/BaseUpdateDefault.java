package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.BaseEntity;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.BaseUpdate;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
public abstract class BaseUpdateDefault<T extends BaseEntity> implements BaseUpdate<T> {

    protected final BaseRepository<T> repository;

    private final static String[] IGNORE_PROPERTIES = {"id", "uuid",
            "createdBy", "updatedBy", "createdAt", "updatedAt"};

    @Override
    public T execute(String uuid, T request) {
        T saved = retrieveOriginalData(uuid);
        T entity = loadDependencies(request, saved);
        BeanUtils.copyProperties(entity, saved, IGNORE_PROPERTIES);
        return repository.save(saved);
    }

    protected abstract T loadDependencies(T request, T saved);

    private T retrieveOriginalData(String uuid) {
        Optional<T> old = repository.findByUuid(uuid);
        if (old.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found");
        }
        return old.get();
    }

}
