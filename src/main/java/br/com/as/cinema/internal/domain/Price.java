package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.enums.PriceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "price_seq", allocationSize = 50, initialValue = 1000)
public class Price extends BaseEntity {

    @Column(name = "description", insertable = true, updatable = true, unique = false, length = 60)
    private String description;

    @Column(name = "details", insertable = true, updatable = true, unique = false, length = 150)
    private String details;

    @Column(name = "amount", insertable = true, updatable = true, unique = false)
    private BigDecimal amount;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    private PriceStatus status;
}
