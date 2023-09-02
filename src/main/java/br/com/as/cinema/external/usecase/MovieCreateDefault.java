package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.repository.MovieRepository;
import br.com.as.cinema.internal.usecase.MovieCreate;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@ConditionalOnSingleCandidate(MovieCreate.class)
public class MovieCreateDefault implements MovieCreate {

    private final MovieRepository repository;

    @Override
    public Movie execute(Movie movie) {
        return repository.save(movie);
    }
}
