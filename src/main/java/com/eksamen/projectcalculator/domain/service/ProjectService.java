package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.repository.DataFacade;
import java.util.List;

public class ProjectService {

    private final DataFacade FACADE = new DataFacade();

    public long createProject(long id, String projectName) throws ProjectException {
        if (!projectName.isEmpty() && projectName.length() <= 50) {
            Project project = new Project();
            project.setUserId(id);
            project.setProjectName(projectName);

            return FACADE.createProject(project);
        } else {
            throw new ProjectException("Invalid input");
        }
    }

    public List<Project> getProjectsByUserId(long userId) {
        return FACADE.getProjectsByUserId(userId);
    }

    public Project getProjectById(long id) {
        Project project = FACADE.getProjectById(id);
        project.setStartDateStr(FACADE.getProjectStartDateById(id));
        project.setDeadlineDateStr(FACADE.getProjectDeadlineById(id));
        return project;
    }

    public boolean projectIsUsers(long userId, long id) {
        return FACADE.projectIsUsers(userId, id);
    }

    public void deleteProjectById(long id) {
        FACADE.deleteProjectById(id);
    }
}
