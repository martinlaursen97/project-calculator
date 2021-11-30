package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.repository.ProjectRepository;

import java.util.ArrayList;

public class ProjectService {

    private final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();

    public void createProject(String projectName) throws ProjectException {
        if (projectName.length() > 0) {
            PROJECT_REPOSITORY.createProject(projectName);
        } else {
            throw new ProjectException("Input field cannot be empty");
        }
    }
}
