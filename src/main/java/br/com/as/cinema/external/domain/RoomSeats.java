package br.com.as.cinema.external.domain;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomSeats {
    private String number;
    private String description;
    private Integer rows;
    private Integer seatsPerRow;
}
