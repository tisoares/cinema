package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.LazyFieldsFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Data
@SequenceGenerator(name = "default_gen", sequenceName = "room_seq", allocationSize = 50, initialValue = 1000)
public class Room extends BaseEntity {

    @Column(name = "number", insertable = true, updatable = true, unique = true, length = 10)
    @EqualsAndHashCode.Exclude
    private String number;

    @Column(name = "description", insertable = true, updatable = true, unique = false, length = 250)
    @EqualsAndHashCode.Exclude
    private String description;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    @JsonIgnoreProperties({"room"})
    @EqualsAndHashCode.Exclude
    private Set<Seat> seats = new HashSet<>();
}
