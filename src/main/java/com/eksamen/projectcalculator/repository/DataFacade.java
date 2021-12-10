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

    public List<User> getUserByEmail(String email) {
        return USER_REPOSITORY.getUserByEmail(email);
    }

    // Project
    public void deleteProjectById(long id) {
        PROJECT_REPOSITORY.deleteProjectById(id);
    }

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

    public boolean projectIsUsers(long userId, long id) {
        return PROJECT_REPOSITORY.projectIsUsers(userId, id);
    }

    // Task
    public void clearTasksByProjectId(long id) {
        TASK_REPOSITORY.clearTasksByProjectId(id);
    }

    public void createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        TASK_REPOSITORY.createTask(projectId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
    }


    public User getUserById(long id) {
        return USER_REPOSITORY.getUserById(id);
    }


    public Task getTaskById(long id) {
        Task task = TASK_REPOSITORY.getTaskById(id);
        List<Subtask> subtasks = SUBTASK_REPOSITORY.getSubtasksByTaskId(task.getTaskId());
        task.setSubtasks(subtasks);
        return task;
    }

    public boolean taskIsUsers(long userId, long taskId) {
        long projectId = TASK_REPOSITORY.getProjectIdByTaskId(taskId);

        return PROJECT_REPOSITORY.projectIsUsers(userId, projectId);
    }


    public long getProjectIdById(long id) {
        return TASK_REPOSITORY.getProjectIdByTaskId(id);
    }

    public void deleteTaskById(long id) {
        TASK_REPOSITORY.deleteTaskById(id);
    }

    public String getProjectStartDateById(long id) {
        return TASK_REPOSITORY.getProjectStartDateById(id);
    }

    public String getProjectDeadlineById(long id) {
        return TASK_REPOSITORY.getProjectDeadlineById(id);
    }

    public void createSubtask(long taskId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        SUBTASK_REPOSITORY.createSubtask(taskId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
    }
}