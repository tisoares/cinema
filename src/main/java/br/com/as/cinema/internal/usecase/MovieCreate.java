package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.internal.domain.Movie;

public interface MovieCreate {
    Movie execute(Movie movie);
}
