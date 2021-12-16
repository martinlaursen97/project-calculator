package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.InvalidDateException;
import com.eksamen.projectcalculator.domain.model.Assignment;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.repository.DataFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Martin
 */

public class TaskService {

    private final DataFacade FACADE = DataFacade.getInstance();

    public long createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) throws InvalidDateException{
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
            Date start = sdf.parse(startDate);
            Date finish = sdf.parse(finishDate);
            if (start.equals(finish)) {
                throw new InvalidDateException("Dates can't be equal");
            }

            if (finish.before(start)) {
                throw new InvalidDateException("Finish date can't be before the start date");
            }

        } catch (InvalidDateException | ParseException e) {
            throw new InvalidDateException(e.getMessage());
        }

        Assignment task = new Task();
        task.setForeignId(projectId);
        task.setName(taskName);
        task.setResource(resource);
        task.setStartDateStr(startDate);
        task.setFinishDateStr(finishDate);
        task.setPercentComplete(completion);
        task.setDailyWorkHours(dailyWorkHours);
        task.setPricePerHour(pricePerHour);
        return FACADE.createTask(task);
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
        Task task = new Task();
        task.setId(taskId);
        task.setPercentComplete(percent);
        FACADE.updateSubtask(task);
    }
}
