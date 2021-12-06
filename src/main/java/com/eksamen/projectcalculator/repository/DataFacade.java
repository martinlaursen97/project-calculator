package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.User;

import java.util.List;

public class DataFacade {
    private final UserRepository USER_REPOSITORY = new UserRepository();
    private final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();

    // User
    public void createUser(String email, String password, boolean isAdmin) throws LoginException {
        USER_REPOSITORY.createUser(email, password, isAdmin);
    }

    public User loginValid(String email, String password) throws LoginException {
        return USER_REPOSITORY.loginValid(email, password);
    }

    // Project
    public void createProject(String projectName) {
        PROJECT_REPOSITORY.createProject(projectName);
    }

    public List<Project> getProjects() {
        return PROJECT_REPOSITORY.getProjects();
    }

    public Project getProjectById(long id) {
        return PROJECT_REPOSITORY.getProjectById(id);
    }


    public List<User> getUser(String user) {
        return USER_REPOSITORY.getUser(user);
    }
}
