package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.configuration.LazyFieldsFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "exhibition_id", insertable = true, updatable = true)
    @JsonIgnoreProperties({"prices"})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private Exhibition exhibition;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "price_id", insertable = true, updatable = true)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private Price price;

    public ExhibitionPrice setExhibition(Exhibition value) {
        if (value != null) {
            this.exhibition = value;
            this.exhibition.getPrices().add(this);
        }
        return this;
    }
}
