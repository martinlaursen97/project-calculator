package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.repository.ProjectRepository;

import java.util.ArrayList;

public class ProjectService {

    private final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();

    public void createProject(String projectName) {
        PROJECT_REPOSITORY.createProject(projectName);

    }

}
