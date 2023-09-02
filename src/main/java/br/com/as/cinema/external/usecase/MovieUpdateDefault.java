package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.BaseUpdate;
import br.com.as.cinema.internal.usecase.MovieUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(MovieUpdate.class)
public class MovieUpdateDefault extends BaseUpdateDefault<Movie> implements MovieUpdate {

    public MovieUpdateDefault(BaseRepository<Movie> repository) {
        super(repository);
    }

    @Override
    protected Movie loadDependencies(Movie request, Movie saved) {
        return request;
    }
}
