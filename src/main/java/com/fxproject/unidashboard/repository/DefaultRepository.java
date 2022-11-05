package com.fxproject.unidashboard.repository;

import java.util.List;
import java.util.Optional;

public interface DefaultRepository<E, T> { // E - model T - id type

    void save(E record);
    void removeWithId(T id);
    Optional<E> findWithId(T id);
    List<E> findAll();
}
