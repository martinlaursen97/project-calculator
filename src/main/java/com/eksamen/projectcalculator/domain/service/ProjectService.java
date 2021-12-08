package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;

import com.eksamen.projectcalculator.repository.DataFacade;

import java.util.List;


public class ProjectService {

    private final DataFacade FACADE = new DataFacade();

    public void createProject(long id, String projectName) throws ProjectException {
        if (projectName.length() > 0 && projectName.length() <= 50) {
            FACADE.createProject(id, projectName);
        } else {
            throw new ProjectException("Invalid input");
        }
    }

    public List<Project> getProjectsByUserId(long userId) {
        return FACADE.getProjectsByUserId(userId);
    }

    public Project getProjectById(long id) {
        return FACADE.getProjectById(id);
    }

    public boolean projectIsUsers(long userId, long id) {
        return FACADE.projectIsUsers(userId, id);
    }

    public void deleteProjectById(long id) {
        FACADE.deleteProjectById(id);
    }
}
