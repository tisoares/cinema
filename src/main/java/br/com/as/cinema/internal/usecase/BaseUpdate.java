package br.com.as.cinema.internal.usecase;

import br.com.as.cinema.internal.domain.BaseEntity;

public interface BaseUpdate<T extends BaseEntity> {

    T execute(String uuid, T entity);
}
