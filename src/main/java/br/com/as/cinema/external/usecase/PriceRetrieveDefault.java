package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.PriceRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(PriceRetrieve.class)
public class PriceRetrieveDefault extends BaseRetrieveDefault<Price> implements PriceRetrieve {

    public PriceRetrieveDefault(BaseRepository<Price> repository) {
        super(repository);
    }
}
