package br.com.as.cinema.external.domain;

import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.enums.ExhibitionStatus;
import br.com.as.cinema.utils.ShortUuidUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExhibitionRequest {
    private String uuid = ShortUuidUtils.generateKey();
    private Movie movie;
    @JsonIgnoreProperties({"seats"})
    private Room room;
    @JsonFormat(pattern = CinemaConstants.DATE_TIME_PATTERN)
    private LocalDateTime exhibitionAt;
    private ExhibitionStatus status;
    private Set<Price> prices;
}
