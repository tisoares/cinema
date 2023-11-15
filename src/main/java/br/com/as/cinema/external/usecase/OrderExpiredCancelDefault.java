package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Order;
import br.com.as.cinema.internal.domain.enums.ExhibitionSeatStatus;
import br.com.as.cinema.internal.domain.enums.OrderStatus;
import br.com.as.cinema.internal.repository.OrderRepository;
import br.com.as.cinema.internal.usecase.OrderExpiredCancel;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(OrderExpiredCancel.class)
public class OrderExpiredCancelDefault implements OrderExpiredCancel {

    private final TransactionTemplate transactionManager;
    private final OrderRepository orderRepository;

    @Override
    public void execute() {
        boolean pending = true;
        while (pending) {
            pending = Boolean.TRUE.equals(transactionManager.execute(trx -> {
                List<Order> orders = orderRepository.findTop50ByStatusAndStartedAtBeforeOrderByStartedAtAsc(OrderStatus.BUYING, LocalDateTime.now());
                if (orders.isEmpty()) {
                    return false;
                }
                orders.forEach(order -> {
                    order.setStatus(OrderStatus.CANCELED);
                    order.getTickets().forEach(ticket -> {
                        ticket.getExhibitionSeat().setStatus(ExhibitionSeatStatus.AVAILABLE);
                    });
                    orderRepository.save(order);
                });
                return true;
            }));
        }
    }
}
