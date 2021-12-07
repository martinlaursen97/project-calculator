package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.repository.DataFacade;

import java.util.Date;

public class TaskService {

    private final DataFacade FACADE = new DataFacade();

    public void createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion) {
        FACADE.createTask(projectId, taskName, resource, startDate, finishDate, completion);
    }

    public void clearTasksByProjectId(long id) {
        FACADE.clearTasksByProjectId(id);
    }
}
