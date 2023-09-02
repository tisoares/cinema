package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seat",
        uniqueConstraints = {
            @UniqueConstraint(name = "seat_uq", columnNames = {"room_id", "row_number", "seat_number"})
        })
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "seat_seq", allocationSize = 50, initialValue = 1000)
public class Seat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "room_id", insertable = true, updatable = true)
    private Room room;

    @Column(name = "row_number", insertable = true, updatable = true, unique = false, length = 5)
    private String row;

    @Column(name = "seat_number", insertable = true, updatable = true, unique = false)
    private Integer number;

    @Column(name = "seat_type", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    private SeatType type;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

}