package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.PriceRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(PriceRetrieve.class)
public class PriceRetrieveImpl extends BaseRetrieveImpl<Price> implements PriceRetrieve {

    public PriceRetrieveImpl(BaseRepository<Price> repository, EntitySpecification entitySpecification) {
        super(repository, entitySpecification);
    }
}
