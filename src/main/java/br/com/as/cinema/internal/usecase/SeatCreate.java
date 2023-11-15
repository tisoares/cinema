package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.external.domain.SeatRequest;
import br.com.as.cinema.internal.domain.Seat;

public interface SeatCreate {

    Seat execute(Seat seat);

    Seat execute(SeatRequest seatRequest);
}
