package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.configuration.EntitySpecification;
import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.repository.MovieRepository;
import br.com.as.cinema.internal.usecase.MovieRetrieve;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(MovieRetrieve.class)
public class MovieRetrieveImpl extends BaseRetrieveImpl<Movie> implements MovieRetrieve {
    public MovieRetrieveImpl(MovieRepository repository, EntitySpecification entitySpecification) {
        super(repository, entitySpecification);
    }
}
