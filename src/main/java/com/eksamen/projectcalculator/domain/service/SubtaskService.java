package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.repository.DataFacade;

public class SubtaskService {
    private final DataFacade FACADE = new DataFacade();
    public void createSubtask(long taskId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        FACADE.createSubtask(taskId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
    }
}
