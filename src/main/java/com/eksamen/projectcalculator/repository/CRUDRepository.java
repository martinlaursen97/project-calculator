package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;

import java.util.List;

public interface CRUDRepository<T> {

    long create(T obj);
    T read(long id);
    void update(T val);
    void delete(long id);

}
