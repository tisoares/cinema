package br.com.as.cinema.internal.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}
