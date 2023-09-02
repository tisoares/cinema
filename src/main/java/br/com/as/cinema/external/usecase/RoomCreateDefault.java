package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatType;
import br.com.as.cinema.internal.repository.RoomRepository;
import br.com.as.cinema.internal.usecase.RoomCreate;
import br.com.as.cinema.internal.usecase.RoomUpdate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@ConditionalOnSingleCandidate(RoomCreate.class)
@AllArgsConstructor
public class RoomCreateDefault implements RoomCreate {

    private final RoomRepository roomRepository;

    @Override
    public Room execute(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room execute(RoomSeats roomSeats) {
        Room room = new Room()
                .setDescription(roomSeats.getDescription())
                .setNumber(roomSeats.getNumber());

        room.setSeats(makeSeats(room, roomSeats.getRows(), roomSeats.getSeatsPerRow()));

        return execute(room);
    }

    private Set<Seat> makeSeats(Room room, Integer rows, Integer seats) {
        Set<Seat> result = new HashSet<>();
        char row = 'A';
        for (int r = 0; r < rows; r++) {
            for (int s = 0; s < seats; s++) {
                result.add(new Seat()
                        .setRoom(room)
                        .setRow(Character.toString(row+r))
                        .setNumber(s+1)
                        .setType(SeatType.NORMAL)
                        .setStatus(SeatStatus.ACTIVE)
                );
            }
        }

        return result;
    }
}
