package br.com.as.cinema.internal.repository;

import br.com.as.cinema.internal.domain.Order;
import br.com.as.cinema.internal.domain.enums.OrderStatus;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.hibernate.LockOptions;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

    @QueryHints({@QueryHint(name = AvailableSettings.JPA_LOCK_TIMEOUT, value = "" + LockOptions.SKIP_LOCKED)})
    @Lock(LockModeType.OPTIMISTIC)
    List<Order> findTop50ByStatusAndStartedAtBeforeOrderByStartedAtAsc(OrderStatus orderStatus, LocalDateTime startedAt);

}
