package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.PriceUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(PriceUpdate.class)
public class PriceUpdateDefault extends BaseUpdateDefault<Price> implements PriceUpdate {
    public PriceUpdateDefault(BaseRepository<Price> repository) {
        super(repository);
    }

    @Override
    protected Price loadDependencies(Price request, Price saved) {
        return request;
    }
}
