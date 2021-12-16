package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;

import java.util.List;

public interface CRUDRepository<T> {

    long create(T obj);
    T read(long id);
    void update(long id, double val);
    void update(long id);
    void delete(long id);
    List<T> getAllById(long id);
    List<T> getAll();

}
