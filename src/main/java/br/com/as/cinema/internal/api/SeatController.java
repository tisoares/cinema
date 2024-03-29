package br.com.as.cinema.internal.api;


import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.external.domain.SeatRequest;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = CinemaConstants.URL_PREFIX_V1 + "seats")
public interface SeatController {
    @GetMapping("/{uuid}")
    Optional<Seat> getByUuid(@PathVariable String uuid);

    @GetMapping
    Page<Seat> getAll(Pageable pageable, SearchCriteria searchCriteria);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Seat create(@RequestBody SeatRequest seatRequest);


    @PutMapping("/{uuid}")
    Seat update(@PathVariable String uuid, @RequestBody Seat seat);
}
