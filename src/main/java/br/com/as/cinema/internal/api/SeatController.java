package br.com.as.cinema.internal.api;


import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = CinemaConstants.URL_PREFIX_V1+"seats")
public interface SeatController {
    @GetMapping("/{uuid}")
    Optional<Seat> getByUuid(@PathVariable String uuid);

    @GetMapping
    Page<Seat> getAll(Pageable pageable);

    @PostMapping
    Seat create(@RequestBody Seat seat);


    @PutMapping("/{uuid}")
    Seat update(@PathVariable String uuid, @RequestBody Seat seat);
}
