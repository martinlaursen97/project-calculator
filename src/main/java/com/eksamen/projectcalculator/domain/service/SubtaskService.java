package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.repository.DataFacade;

public class SubtaskService {
    private final DataFacade FACADE = new DataFacade();

    public long createSubtask(long taskId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        return FACADE.createSubtask(taskId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
    }

    public Subtask getSubtaskById(long subtaskId) {
        return FACADE.getSubtaskById(subtaskId);
    }

    public boolean subtaskIsUsers(long userId, long subtaskId) {
        return FACADE.subtaskIsUsers(userId, subtaskId);
    }

    public long getProjectIdBySubtaskId(long subtaskId) {
        return FACADE.getProjectIdBySubtaskId(subtaskId);
    }

    public void deleteSubtaskById(long subtaskId) {
        FACADE.deleteSubtaskById(subtaskId);
    }

    public void updateSubtaskPercentById(long subtaskId, int percent) {
        FACADE.updateSubtaskPercentById(subtaskId, percent);
    }
}
