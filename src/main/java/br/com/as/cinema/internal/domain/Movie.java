package br.com.as.cinema.internal.domain;


import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.enums.MovieStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "movie")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "movie_seq", allocationSize = 50, initialValue = 1000)
public class Movie extends BaseEntity {

    @Column(name = "name", insertable = true, updatable = true, unique = false, length = 150)
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "description", insertable = true, updatable = true, unique = false, length = 250)
    @EqualsAndHashCode.Include
    private String description;

    @Column(name = "duration", insertable = true, updatable = true, unique = false, length = 10)
    @EqualsAndHashCode.Include
    private String duration;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private MovieStatus status = MovieStatus.ACTIVE;

}
