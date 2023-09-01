package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "ticket_seq", allocationSize = 50, initialValue = 1000)
public class Ticket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "exhibition_seat_id", insertable = true, updatable = true)
    private ExhibitionSeat exhibitionSeat;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id", insertable = true, updatable = true)
    private Order order;

}
