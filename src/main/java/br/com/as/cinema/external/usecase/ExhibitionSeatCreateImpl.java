package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.ExhibitionSeat;
import br.com.as.cinema.internal.repository.ExhibitionSeatRepository;
import br.com.as.cinema.internal.usecase.ExhibitionSeatCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionSeatCreate.class)
public class ExhibitionSeatCreateImpl implements ExhibitionSeatCreate {

    private final ExhibitionSeatRepository repository;

    @Override
    public ExhibitionSeat execute(ExhibitionSeat exhibitionSeat) {
        return repository.save(exhibitionSeat);
    }
}
