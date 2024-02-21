package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.repository.PriceRepository;
import br.com.as.cinema.internal.usecase.PriceCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(PriceCreate.class)
@AllArgsConstructor
public class PriceCreateImpl implements PriceCreate {

    private final PriceRepository priceRepository;

    @Override
    public Price execute(Price price) {
        return priceRepository.save(price);
    }

}
