package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Task;

import java.util.List;

public interface TaskRepository extends CRUDRepository<Task> {
    List<Task> getTasksByProjectId(long projectId);
    void clearTasksByProjectId(long id);
    long getProjectIdByTaskId(long taskId);
    String getProjectStartDateById(long projectId);
    String getProjectDeadlineById(long projectId);
}
