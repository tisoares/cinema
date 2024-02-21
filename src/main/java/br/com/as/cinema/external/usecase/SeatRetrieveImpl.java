package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.Seat;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.SeatRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(SeatRetrieve.class)
public class SeatRetrieveImpl extends BaseRetrieveImpl<Seat> implements SeatRetrieve {

    public SeatRetrieveImpl(BaseRepository<Seat> repository, EntitySpecification entitySpecification) {
        super(repository, entitySpecification);
    }
}
