package br.com.as.cinema.internal.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
@SequenceGenerator(name = "default_gen", sequenceName = "room_seq", allocationSize = 50, initialValue = 1000)
public class Room extends BaseEntity {

    @Column(name = "number", insertable = true, updatable = true, unique = true, length = 10)
    private String number;
    @Column(name = "description", insertable = true, updatable = true, unique = false, length = 250)
    private String description;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference
    private Set<Seat> seats;
}
