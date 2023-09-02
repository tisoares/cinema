package br.com.as.cinema.internal.repository;

import br.com.as.cinema.internal.domain.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository< T , Long>, CrudRepository<T , Long> {

//    @QueryHints({@QueryHint(name = AvailableSettings.JPA_LOCK_TIMEOUT, value = ""+LockOptions.SKIP_LOCKED)})
//    @Lock(LockModeType.OPTIMISTIC)

    Optional<T> findByUuid(String uuid);
    Page<T> findAll(Pageable pageable);
    boolean existsByUuid(String uuid);
}
