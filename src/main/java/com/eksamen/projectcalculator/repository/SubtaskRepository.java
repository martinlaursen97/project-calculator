package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Subtask;

import java.util.List;

public interface SubtaskRepository extends CRUDRepository<Subtask> {

    List<Subtask> getSubtasksByTaskId(long taskId);
    long getTaskIdBySubtaskId(long subtaskId);
}
