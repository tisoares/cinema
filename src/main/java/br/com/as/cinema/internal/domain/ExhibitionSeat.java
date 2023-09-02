package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.domain.enums.ExhibitionSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exhibition_seat",
        uniqueConstraints = {
                @UniqueConstraint(name = "exhibition_seat_uq", columnNames = {"exhibition_id", "seat_id"})
        })
@Getter
@Setter
@SequenceGenerator(name = "default_gen", sequenceName = "exhibition_seat_seq", allocationSize = 50, initialValue = 1000)
public class ExhibitionSeat extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exhibition_id", insertable = true, updatable = true)
    private Exhibition exhibition;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_id", insertable = true, updatable = true)
    private Seat seat;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    private ExhibitionSeatStatus status;
}
