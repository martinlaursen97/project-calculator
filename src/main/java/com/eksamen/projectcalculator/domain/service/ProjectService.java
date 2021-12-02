package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.repository.DataFacade;
import com.eksamen.projectcalculator.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private final DataFacade FACADE = new DataFacade();

    public void createProject(String projectName) throws ProjectException {
        if (projectName.length() > 0 && projectName.length() <= 50) {
            FACADE.createProject(projectName);
        } else {
            throw new ProjectException("Invalid input");
        }
    }

    public List<Project> getProjects() {
        return FACADE.getProjects();
    }

    public Project getProjectById(long id) {
        return FACADE.getProjectById(id);
    }
}
