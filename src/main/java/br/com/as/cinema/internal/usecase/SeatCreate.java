package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.internal.domain.Seat;

public interface SeatCreate {

    Seat execute(Seat seat);
}
