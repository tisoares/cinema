package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.ExhibitionPrice;
import br.com.as.cinema.internal.repository.ExhibitionPriceRepository;
import br.com.as.cinema.internal.usecase.ExhibitionPriceCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(ExhibitionPriceCreate.class)
public class ExhibitionPriceCreateImpl implements ExhibitionPriceCreate {

    private final ExhibitionPriceRepository repository;

    @Override
    public ExhibitionPrice execute(ExhibitionPrice price) {
        return repository.save(price);
    }
}
