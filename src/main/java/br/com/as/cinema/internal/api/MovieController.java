package br.com.as.cinema.internal.api;

import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.configuration.CinemaConstants;
import br.com.as.cinema.internal.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(value = CinemaConstants.URL_PREFIX_V1 + "movies")
public interface MovieController {

    @GetMapping("/{uuid}")
    Optional<Movie> getByUuid(@PathVariable String uuid);

    @GetMapping
    Page<Movie> getAll(Pageable pageable, SearchCriteria searchCriteria);

    @PostMapping
    Movie create(@RequestBody Movie movie);

    @PutMapping("/{uuid}")
    Movie update(@PathVariable String uuid, @RequestBody Movie movie);
}
