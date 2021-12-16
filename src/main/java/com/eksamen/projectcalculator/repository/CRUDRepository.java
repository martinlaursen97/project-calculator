package com.eksamen.projectcalculator.repository;

public interface CRUDRepository<T> {

    long create(T obj);
    T read(long id);
    void update(T val);
    void delete(long id);

}
