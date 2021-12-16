package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.*;

import java.util.List;

/**
 * @author Martin
 */

public class DataFacade {
    private static DataFacade instance = null;

    private final SubtaskRepository SUBTASK_REPOSITORY;
    private final UserRepository USER_REPOSITORY;
    private final ProjectRepository PROJECT_REPOSITORY;
    private final TaskRepository TASK_REPOSITORY;

    public DataFacade() {
        SUBTASK_REPOSITORY = new SubtaskRepositoryImpl();
        USER_REPOSITORY = new UserRepositoryImpl();
        PROJECT_REPOSITORY = new ProjectRepositoryImpl();
        TASK_REPOSITORY = new TaskRepositoryImpl();
    }

    public static DataFacade getInstance() {
        if (instance == null) {
            instance = new DataFacade();
        }
        return instance;
    }

    // -------------------------------- User --------------------------------

    public long createUser(User user) {
        return USER_REPOSITORY.create(user);
    }

    public User loginValid(String email, String password) throws LoginException {
        return USER_REPOSITORY.loginValid(email, password);
    }

    public List<User> getUsers() {
        return USER_REPOSITORY.getAll();
    }

    public void changeAdmin(User user) {
        USER_REPOSITORY.update(user);
    }

    public List<User> getUserByKey(String key) {
        return USER_REPOSITORY.getUserByKey(key);
    }

    public boolean emailExists(String email) {
        return USER_REPOSITORY.emailExists(email);
    }

    public void deleteUserById(long id) {
        USER_REPOSITORY.delete(id);
    }


    //  -------------------------------- Project --------------------------------

    public void deleteProjectById(long id) {
        PROJECT_REPOSITORY.delete(id);
    }

    public long createProject(Project project) {
        return PROJECT_REPOSITORY.create(project);
    }

    public List<Project> getProjectsByUserId(long userId) {
        return PROJECT_REPOSITORY.getProjectsByUserId(userId);
    }

    public Project getProjectById(long id) {
        Project project = PROJECT_REPOSITORY.read(id);
        List<Task> tasks = TASK_REPOSITORY.getTasksByProjectId(id);

        for (Task task : tasks) {
            List<Subtask> subtasks = SUBTASK_REPOSITORY.getSubtasksByTaskId(task.getId());
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


    // -------------------------------- Task --------------------------------

    public void clearTasksByProjectId(long id) {
        TASK_REPOSITORY.clearTasksByProjectId(id);
    }

    public long createTask(Assignment task) {
        return TASK_REPOSITORY.create((Task) task);
    }

    public User getUserById(long id) {
        return USER_REPOSITORY.read(id);
    }

    public Task getTaskById(long id) {
        Task task = TASK_REPOSITORY.read(id);
        List<Subtask> subtasks = SUBTASK_REPOSITORY.getSubtasksByTaskId(task.getId());
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
        TASK_REPOSITORY.delete(id);
    }

    public String getProjectStartDateById(long id) {
        return TASK_REPOSITORY.getProjectStartDateById(id);
    }

    public String getProjectDeadlineById(long id) {
        return TASK_REPOSITORY.getProjectDeadlineById(id);
    }

    public void updateSubtask(Task task) {
        TASK_REPOSITORY.update(task);
    }


    // -------------------------------- Subtask --------------------------------

    public long createSubtask(Subtask subtask) {
        return SUBTASK_REPOSITORY.create(subtask);
    }

    public Task getTaskBySubtaskId(long subtaskId) {
        long taskId = SUBTASK_REPOSITORY.getTaskIdBySubtaskId(subtaskId);
        return TASK_REPOSITORY.read(taskId);
    }

    public Subtask getSubtaskById(long subtaskId) {
        return SUBTASK_REPOSITORY.read(subtaskId);
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
        SUBTASK_REPOSITORY.delete(subtaskId);
    }

    public void updateSubtask(Subtask subtask) {
        SUBTASK_REPOSITORY.update(subtask);
    }
}