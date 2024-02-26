package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.enums.PriceStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "price_seq", allocationSize = 50, initialValue = 1000)
public class Price extends BaseEntity {

    @Column(name = "description", insertable = true, updatable = true, unique = false, length = 60)
    @EqualsAndHashCode.Include
    private String description;

    @Column(name = "details", insertable = true, updatable = true, unique = false, length = 150)
    @EqualsAndHashCode.Include
    private String details;

    @Column(name = "amount", insertable = true, updatable = true, unique = false)
    @EqualsAndHashCode.Include
    private BigDecimal amount;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private PriceStatus status;
}
