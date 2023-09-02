package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.repository.MovieRepository;
import br.com.as.cinema.internal.usecase.MovieRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(MovieRetrieve.class)
public class MovieRetrieveDefault extends BaseRetrieveDefault<Movie> implements MovieRetrieve {
    public MovieRetrieveDefault(MovieRepository repository) {
        super(repository);
    }
}
