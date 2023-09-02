package br.com.as.cinema.external.usecase;

import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.internal.domain.Room;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.domain.enums.SeatStatus;
import br.com.as.cinema.internal.domain.enums.SeatType;
import br.com.as.cinema.internal.repository.RoomRepository;
import br.com.as.cinema.internal.repository.SeatRepository;
import br.com.as.cinema.internal.usecase.RoomCreate;
import br.com.as.cinema.internal.usecase.SeatCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@ConditionalOnSingleCandidate(SeatCreate.class)
@AllArgsConstructor
public class SeatCreateDefault implements SeatCreate {

    private final SeatRepository seatRepository;

    @Override
    public Seat execute(Seat seat) {
        return seatRepository.save(seat);
    }

}
