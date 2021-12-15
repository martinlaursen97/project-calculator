package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.repository.DataFacade;

public class TaskService {

    private final DataFacade FACADE = new DataFacade();

    public long createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        return FACADE.createTask(projectId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
    }

    public void clearTasksByProjectId(long id) {
        FACADE.clearTasksByProjectId(id);
    }

    public Task getTaskById(long id) {
        return FACADE.getTaskById(id);
    }

    public boolean taskIsUsers(long userId, long id) {
        return FACADE.taskIsUsers(userId, id);
    }

    public long getProjectIdById(long id) {
        return FACADE.getProjectIdById(id);
    }

    public void deleteTaskById(long id) {
        FACADE.deleteTaskById(id);
    }

    public Task getTaskBySubtaskId(long subtaskId) {
        return FACADE.getTaskBySubtaskId(subtaskId);
    }

    public void updateTaskPercentById(long taskId, int percent) {
        FACADE.updateTaskPercentById(taskId, percent);
    }
}
