package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.configuration.LazyFieldsFilter;
import br.com.as.cinema.internal.domain.enums.OrderStatus;
import br.com.as.cinema.internal.domain.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "ticket_order")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "order_seq", allocationSize = 50, initialValue = 1000)
public class Order extends BaseEntity {

    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "started_at")
    @EqualsAndHashCode.Include
    private LocalDateTime startedAt;

    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "paid_at")
    @EqualsAndHashCode.Include
    private LocalDateTime paidAt;

    @Column(name = "total")
    @EqualsAndHashCode.Include
    private BigDecimal total;

    @Column(name = "status", length = 15)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private OrderStatus status;

    @Column(name = "payment_type", length = 15)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties({"order"})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties({"order"})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private Set<OrderPrice> orderPrices;
}
