package br.com.as.cinema.external.usecase;

import br.com.as.cinema.internal.domain.Movie;
import br.com.as.cinema.internal.repository.BaseRepository;
import br.com.as.cinema.internal.usecase.MovieUpdate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnSingleCandidate(MovieUpdate.class)
public class MovieUpdateImpl extends BaseUpdateImpl<Movie> implements MovieUpdate {

    public MovieUpdateImpl(BaseRepository<Movie> repository) {
        super(repository);
    }

    @Override
    protected Movie loadDependencies(Movie request, Movie saved) {
        return request;
    }
}
