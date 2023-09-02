package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.enums.OrderStatus;
import br.com.as.cinema.internal.domain.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ticket_order")
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "order_seq", allocationSize = 50, initialValue = 1000)
public class Order extends BaseEntity {

    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "status", length = 15)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "payment_type", length = 15)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "order")
    private Set<OrderPrice> orderPrices;
}
