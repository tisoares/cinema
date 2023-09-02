package br.com.as.cinema.internal.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.enums.ExhibitionStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "exhibition")
@Getter
@Setter
@SequenceGenerator(name = CinemaConstants.DEFAULT_SEQUENCE_NAME, sequenceName = "exhibition_seq", allocationSize = 50, initialValue = 1000)
public class Exhibition extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", insertable = true, updatable = true)
    private Movie movie;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", insertable = true, updatable = true)
    private Room room;

    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    @Column(name = "exhibition_at")
    private LocalDateTime exhibitionAt;

    @Column(name = "status", insertable = true, updatable = true, unique = false, length = 15)
    @Enumerated(EnumType.STRING)
    private ExhibitionStatus status;

    @OneToMany(mappedBy = "exhibition")
    @JsonManagedReference
    private Set<ExhibitionPrice> prices;

    @OneToMany(mappedBy = "exhibition")
    @JsonManagedReference
    private Set<ExhibitionSeat> seats;
}
