package org.mushroom.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<E, ID> {

    Optional<E> findOne(ID id);

    E findById(ID id);

    List<E> findAll();

    E create(E object);

    E update(E object);

    void delete(ID id);

    void softDelete(ID id);
}