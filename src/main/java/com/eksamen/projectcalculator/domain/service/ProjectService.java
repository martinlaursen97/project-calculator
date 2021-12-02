package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.repository.ProjectRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();

    public void createProject(String projectName) throws ProjectException {
        if (projectName.length() > 0 && projectName.length() <= 50) {
            PROJECT_REPOSITORY.createProject(projectName);
        } else {
            throw new ProjectException("Invalid input");
        }
    }

    public List<Project> getProjects() {

        return PROJECT_REPOSITORY.getProjects();
    }

    public Project getProjectById(long id) {
        return PROJECT_REPOSITORY.getProjectById(id);
    }
}
