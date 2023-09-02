package br.com.as.cinema.internal.repository;

import br.com.as.cinema.internal.domain.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends BaseRepository<Movie> {
}
