package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exhibition_price",
        uniqueConstraints = {
                @UniqueConstraint(name = "exhibition_price_uq", columnNames = {"exhibition_id", "price_id"})
        })
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "exhibition_price_seq", allocationSize = 50, initialValue = 1000)
public class ExhibitionPrice extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "exhibition_id", insertable = true, updatable = true)
    @JsonBackReference
    private Exhibition exhibition;

    @ManyToOne
    @JoinColumn(name = "price_id", insertable = true, updatable = true)
    private Price price;
}
