package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataFacade {
    private final SubtaskRepository SUBTASK_REPOSITORY = new SubtaskRepository();
    private final UserRepository USER_REPOSITORY = new UserRepository();
    private final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();
    private final TaskRepository TASK_REPOSITORY = new TaskRepository();

    // User
    public void createUser(String email, String password, boolean isAdmin) throws LoginException {
        USER_REPOSITORY.createUser(email, password, isAdmin);
    }

    public User loginValid(String email, String password) throws LoginException {
        return USER_REPOSITORY.loginValid(email, password);
    }

    public List<User> getUsers() {
        return USER_REPOSITORY.getUsers();
    }

    // Project
    public void createProject(long id, String projectName) {
        PROJECT_REPOSITORY.createProject(id, projectName);
    }

    public List<Project> getProjectsByUserId(long userId) {
        return PROJECT_REPOSITORY.getProjectsByUserId(userId);
    }

    public Project getProjectById(long id) {
        Project project = PROJECT_REPOSITORY.getProjectById(id);
        List<Task> tasks = TASK_REPOSITORY.getTasksByProjectId(id);

        for (Task task : tasks) {
            List<Subtask> subtasks = SUBTASK_REPOSITORY.getSubtasksByTaskId(task.getTaskId());
            if (subtasks != null) {
                task.setSubtasks(subtasks);
            }
        }

        project.setTasks(tasks);
        return project;
    }


    public void createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion) {
        TASK_REPOSITORY.createTask(projectId, taskName, resource, startDate, finishDate, completion);
    }

    public boolean projectIsUsers(long userId, long id) {
        return PROJECT_REPOSITORY.projectIsUsers(userId, id);
    }

    public List<User> getUserByEmail(String email) {
        return USER_REPOSITORY.getUserByEmail(email);
    }
}