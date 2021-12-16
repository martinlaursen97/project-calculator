package com.eksamen.projectcalculator.repository;

/**
 * @author Martin
 */

// Indeholder de basale crud operationer som et repository skal kunne udføre
public interface CRUDRepository<T> {

    long create(T obj);
    T read(long id);
    void update(T val);
    void delete(long id);

}
