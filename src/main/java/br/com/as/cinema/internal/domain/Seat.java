package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.configuration.LazyFieldsFilter;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "seat",
        uniqueConstraints = {
                @UniqueConstraint(name = "seat_uq", columnNames = {"room_id", "row_letter", "seat_number"})
        })
@Data
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "seat_seq", allocationSize = 50, initialValue = 1000)
public class Seat extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", insertable = true, updatable = true)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    @JsonIgnoreProperties(value = {"seats"})
    @EqualsAndHashCode.Exclude
    private Room room;

    @Column(name = "row_letter", insertable = true, updatable = true, unique = false, length = 1)
    @EqualsAndHashCode.Include
    private String row;

    @Column(name = "seat_number", insertable = true, updatable = true, unique = false)
    @EqualsAndHashCode.Include
    private Integer number;

    @Column(name = "seat_type", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private SeatType type = SeatType.NORMAL;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private SeatStatus status = SeatStatus.ACTIVE;

    public Seat setRoom(Room room) {
        if (room != null) {
            this.room = room;
            room.getSeats().add(this);
        }
        return this;
    }
}
