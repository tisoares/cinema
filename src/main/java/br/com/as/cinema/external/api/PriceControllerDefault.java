package br.com.as.cinema.external.api;

import br.com.as.cinema.internal.api.PriceController;
import br.com.as.cinema.internal.domain.Price;
import br.com.as.cinema.internal.usecase.PriceCreate;
import br.com.as.cinema.internal.usecase.PriceRetrieve;
import br.com.as.cinema.internal.usecase.PriceUpdate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(PriceController.class)
public class PriceControllerDefault implements PriceController {

    private final PriceRetrieve priceRetrieve;
    private final PriceCreate priceCreate;
    private final PriceUpdate priceUpdate;

    @Override
    public Optional<Price> getByUuid(String uuid) {
        return priceRetrieve.execute(uuid);
    }

    @Override
    public Page<Price> getAll(Pageable pageable) {
        return priceRetrieve.execute(pageable);
    }

    @Override
    public Price create(Price price) {
        return priceCreate.execute(price);
    }

    @Override
    public Price update(String uuid, Price price) {
        return priceUpdate.execute(uuid, price);
    }
}
