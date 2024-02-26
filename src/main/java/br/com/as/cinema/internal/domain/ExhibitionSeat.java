package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.LazyFieldsFilter;
import br.com.as.cinema.internal.domain.enums.ExhibitionSeatStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "exhibition_seat",
        uniqueConstraints = {
                @UniqueConstraint(name = "exhibition_seat_uq", columnNames = {"exhibition_id", "seat_id"})
        })
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "default_gen", sequenceName = "exhibition_seat_seq", allocationSize = 50, initialValue = 1000)
public class ExhibitionSeat extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "exhibition_id", insertable = true, updatable = true)
    @JsonIgnoreProperties({"seats"})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private Exhibition exhibition;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "seat_id", insertable = true, updatable = true)
    @JsonIgnoreProperties({"uuid", "room"})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private Seat seat;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    private ExhibitionSeatStatus status;

    public ExhibitionSeat setExhibition(Exhibition value) {
        if (value != null) {
            this.exhibition = value;
            this.exhibition.getSeats().add(this);
        }
        return this;
    }

}
