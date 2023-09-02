package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_price")
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "order_price_seq", allocationSize = 50, initialValue = 1000)
public class OrderPrice extends BaseEntity {

    @JoinColumn(name = "amount", insertable = true, updatable = true)
    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "order_id", insertable = true, updatable = true)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exhibition_price_id", insertable = true, updatable = true)
    private ExhibitionPrice exhibitionPrice;
}
