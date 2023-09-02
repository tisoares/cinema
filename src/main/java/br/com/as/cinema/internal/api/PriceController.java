package br.com.as.cinema.internal.api;


import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = CinemaConstants.URL_PREFIX_V1 + "prices")
public interface PriceController {
    @GetMapping("/{uuid}")
    Optional<Price> getByUuid(@PathVariable String uuid);

    @GetMapping
    Page<Price> getAll(Pageable pageable);

    @PostMapping
    Price create(@RequestBody Price price);


    @PutMapping("/{uuid}")
    Price update(@PathVariable String uuid, @RequestBody Price price);
}
