package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.model.User;
import java.util.List;

public class DataFacade {
    private final SubtaskRepository SUBTASK_REPOSITORY = new SubtaskRepository();
    private final UserRepository USER_REPOSITORY = new UserRepository();
    private final ProjectRepository PROJECT_REPOSITORY = new ProjectRepository();
    private final TaskRepository TASK_REPOSITORY = new TaskRepository();

    // User
    public long createUser(String email, String password, boolean isAdmin) throws LoginException {
        if (!USER_REPOSITORY.emailExists(email)) {
            return USER_REPOSITORY.createUser(email, password, isAdmin);
        } else {
            throw new LoginException("Email taken");
        }
    }

    public User loginValid(String email, String password) throws LoginException {
        return USER_REPOSITORY.loginValid(email, password);
    }

    public List<User> getUsers() {
        return USER_REPOSITORY.getUsers();
    }

    public void changeAdmin(Long userId) {
        USER_REPOSITORY.changeAdmin(userId);
    }

    public List<User> getUserByKey(String key) {
        return USER_REPOSITORY.getUserByKey(key);
    }

    // Project
    public void deleteProjectById(long id) {
        PROJECT_REPOSITORY.deleteProjectById(id);
    }

    public long createProject(long id, String projectName) {
        return PROJECT_REPOSITORY.createProject(id, projectName);
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

    public long createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        return TASK_REPOSITORY.createTask(projectId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
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

    public void updateTaskPercentById(long taskId, int percent) {
        TASK_REPOSITORY.updateTaskPercentById(taskId, percent);
    }

    // Subtask
    public long createSubtask(long taskId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        return SUBTASK_REPOSITORY.createSubtask(taskId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
    }

    public Task getTaskBySubtaskId(long subtaskId) {
        long taskId = SUBTASK_REPOSITORY.getTaskIdBySubtaskId(subtaskId);
        return TASK_REPOSITORY.getTaskById(taskId);
    }

    public Subtask getSubtaskById(long subtaskId) {
        return SUBTASK_REPOSITORY.getSubtaskById(subtaskId);
    }

    public boolean subtaskIsUsers(long userId, long subtaskId) {
        long taskId = SUBTASK_REPOSITORY.getTaskIdBySubtaskId(subtaskId);
        long projectId = TASK_REPOSITORY.getProjectIdByTaskId(taskId);
        return PROJECT_REPOSITORY.projectIsUsers(userId, projectId);
    }

    public long getProjectIdBySubtaskId(long subtaskId) {
        long taskId = SUBTASK_REPOSITORY.getTaskIdBySubtaskId(subtaskId);
        return TASK_REPOSITORY.getProjectIdByTaskId(taskId);
    }

    public void deleteSubtaskById(long subtaskId) {
        SUBTASK_REPOSITORY.deleteSubtaskById(subtaskId);
    }

    public void updateSubtaskPercentById(long subtaskId, int percent) {
        SUBTASK_REPOSITORY.updateSubtaskPercentById(subtaskId, percent);
    }
}