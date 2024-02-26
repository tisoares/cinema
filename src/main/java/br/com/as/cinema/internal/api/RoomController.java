package br.com.as.cinema.internal.api;


import br.com.as.cinema.external.domain.RoomSeats;
import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = CinemaConstants.URL_PREFIX_V1 + "rooms")
public interface RoomController {
    @GetMapping("/{uuid}")
    Optional<Room> getByUuid(@PathVariable String uuid);

    @GetMapping
    Page<Room> getAll(Pageable pageable, SearchCriteria searchCriteria);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Room create(@RequestBody Room room);

    @PostMapping("/seats")
    @ResponseStatus(HttpStatus.CREATED)
    Room create(@RequestBody RoomSeats roomSeats);

    @PutMapping("/{uuid}")
    Room update(@PathVariable String uuid, @RequestBody Room room);
}
