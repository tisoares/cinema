package br.com.as.cinema.external.api;

import br.com.as.cinema.external.domain.SeatRequest;
import br.com.as.cinema.internal.api.SeatController;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.usecase.SeatCreate;
import br.com.as.cinema.internal.usecase.SeatRetrieve;
import br.com.as.cinema.internal.usecase.SeatUpdate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(SeatController.class)
public class SeatControllerDefault implements SeatController {

    private final SeatRetrieve seatRetrieve;
    private final SeatCreate seatCreate;
    private final SeatUpdate seatUpdate;

    @Override
    public Optional<Seat> getByUuid(String uuid) {
        return seatRetrieve.execute(uuid);
    }

    @Override
    public Page<Seat> getAll(Pageable pageable) {
        return seatRetrieve.execute(pageable);
    }

    @Override
    public Seat create(SeatRequest seatRequest) {
        return seatCreate.execute(seatRequest);
    }

    @Override
    public Seat update(String uuid, Seat seat) {
        return seatUpdate.execute(uuid, seat);
    }
}
