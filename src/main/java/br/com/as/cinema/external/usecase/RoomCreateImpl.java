package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatType;
import br.com.as.cinema.internal.repository.RoomRepository;
import br.com.as.cinema.internal.usecase.RoomCreate;
import br.com.as.cinema.internal.usecase.SeatCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@ConditionalOnSingleCandidate(RoomCreate.class)
@AllArgsConstructor
public class RoomCreateImpl implements RoomCreate {

    private final TransactionTemplate transactionManager;

    private final RoomRepository roomRepository;
    private final SeatCreate seatCreate;

    @Override
    public Room execute(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room execute(RoomSeats roomSeats) {
        return transactionManager.execute(trx -> {
            Room room = new Room()
                    .setDescription(roomSeats.getDescription())
                    .setNumber(roomSeats.getNumber());
            room = execute(room);
            makeSeats(room, roomSeats.getRows(), roomSeats.getSeatsPerRow());
            return room;
        });
    }

    private void makeSeats(Room room, Integer rows, Integer seats) {
        char row = 'A';
        for (int r = 0; r < rows; r++) {
            for (int s = 0; s < seats; s++) {
                Seat seat = new Seat()
                        .setRoom(room)
                        .setRow(Character.toString(row + r))
                        .setNumber(s + 1)
                        .setType(SeatType.NORMAL)
                        .setStatus(SeatStatus.ACTIVE);
                seatCreate.execute(seat);
            }
        }
    }

}
