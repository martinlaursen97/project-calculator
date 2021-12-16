package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;

import java.util.List;

public interface ProjectRepository extends CRUDRepository<Project> {
    List<Project> getProjectsByUserId(long id);
    boolean projectIsUsers(long userId, long id);
}
