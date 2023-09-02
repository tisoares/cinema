package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.BaseEntity;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.BaseRetrieve;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public abstract class  BaseRetrieveDefault<T extends BaseEntity> implements BaseRetrieve<T> {

    private final BaseRepository<T> repository;
    @Override
    public Optional<T> execute(String uuid) {
        return repository.findByUuid(uuid);
    }

    @Override
    public Page<T> execute(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
