package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.internal.domain.Room;

public interface RoomCreate {

    Room execute(Room room);
    Room execute(RoomSeats roomSeats);
}
