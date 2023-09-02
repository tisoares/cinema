package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.SeatRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(SeatRetrieve.class)
public class SeatRetrieveDefault extends BaseRetrieveDefault<Seat> implements SeatRetrieve {

    public SeatRetrieveDefault(BaseRepository<Seat> repository) {
        super(repository);
    }
}
