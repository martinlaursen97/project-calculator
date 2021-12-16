package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.InvalidDateException;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.repository.DataFacade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubtaskService {

    private final DataFacade FACADE = DataFacade.getInstance();

    public long createSubtask(long taskId, String subtaskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) throws InvalidDateException{
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

        Subtask subtask = new Subtask();
        subtask.setId(taskId);
        subtask.setName(subtaskName);
        subtask.setResource(resource);
        subtask.setStartDateStr(startDate);
        subtask.setFinishDateStr(finishDate);
        subtask.setPercentComplete(completion);
        subtask.setDailyWorkHours(dailyWorkHours);
        subtask.setPricePerHour(pricePerHour);
        return FACADE.createSubtask(subtask);
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
        Subtask subtask = new Subtask();
        subtask.setId(subtaskId);
        subtask.setPercentComplete(percent);
        FACADE.updateSubtask(subtask);
    }
}
