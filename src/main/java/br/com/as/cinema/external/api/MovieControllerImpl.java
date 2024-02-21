package br.com.as.cinema.external.api;

import br.com.as.cinema.external.domain.SearchCriteria;
import br.com.as.cinema.internal.api.MovieController;
import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.usecase.MovieCreate;
import br.com.as.cinema.internal.usecase.MovieRetrieve;
import br.com.as.cinema.internal.usecase.MovieUpdate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@ConditionalOnSingleCandidate(MovieController.class)
public class MovieControllerImpl implements MovieController {

    private final MovieRetrieve movieRetrieve;
    private final MovieCreate movieCreate;
    private final MovieUpdate movieUpdate;

    @Override
    public Optional<Movie> getByUuid(String uuid) {
        return movieRetrieve.execute(uuid);
    }

    @Override
    public Page<Movie> getAll(Pageable pageable, SearchCriteria searchCriteria) {
        return movieRetrieve.execute(pageable, searchCriteria);
    }

    @Override
    public Movie create(Movie movie) {
        return movieCreate.execute(movie);
    }

    @Override
    public Movie update(String uuid, Movie movie) {
        return movieUpdate.execute(uuid, movie);
    }
}
