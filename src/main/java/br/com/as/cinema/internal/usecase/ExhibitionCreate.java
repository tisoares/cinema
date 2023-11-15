package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.external.domain.ExhibitionRequest;
import br.com.as.cinema.internal.domain.Exhibition;

public interface ExhibitionCreate {
    Exhibition execute(Exhibition exhibition);

    Exhibition execute(ExhibitionRequest exhibitionRequest);
}
