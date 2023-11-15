package br.com.as.cinema.external.domain;

import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.enums.ExhibitionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExhibitionRequest {
    private String uuid;
    private Movie movie;
    private Room room;
    private LocalDateTime exhibitionAt;
    private ExhibitionStatus status;
    private Set<Price> prices;
}
