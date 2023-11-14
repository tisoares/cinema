package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.repository.SeatRepository;
import br.com.as.cinema.internal.usecase.SeatCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

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
