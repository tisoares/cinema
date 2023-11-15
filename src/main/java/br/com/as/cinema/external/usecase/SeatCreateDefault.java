package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.SeatRequest;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.repository.SeatRepository;
import br.com.as.cinema.internal.usecase.RoomRetrieve;
import br.com.as.cinema.internal.usecase.SeatCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(SeatCreate.class)
@AllArgsConstructor
public class SeatCreateDefault implements SeatCreate {

    private final SeatRepository seatRepository;
    private final RoomRetrieve roomRetrieve;

    @Override
    public Seat execute(Seat seat) {
        return seatRepository.save(
                seat.setRoom(
                        roomRetrieve.execute(seat.getRoom()
                        )));
    }

    @Override
    public Seat execute(SeatRequest seatRequest) {
        Seat seat = new Seat()
                .setRoom(seatRequest.getRoom())
                .setNumber(seatRequest.getNumber())
                .setRow(seatRequest.getRow())
                .setType(seatRequest.getType())
                .setStatus(seatRequest.getStatus());
        seat.setUuid(seatRequest.getUuid());
        return this.execute(seat);
    }
}
