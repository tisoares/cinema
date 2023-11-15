package br.com.as.cinema.external.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomSeats {
    private String number;
    private String description;
    private Integer rows;
    private Integer seatsPerRow;
}
