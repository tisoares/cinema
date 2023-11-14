package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.internal.domain.Exhibition;

public interface ExhibitionUpdate {
    Exhibition execute(String uuid, Exhibition exhibition);
}
