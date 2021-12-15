package com.eksamen.projectcalculator.domain.model;

import com.eksamen.projectcalculator.util.Calculator;

import java.util.List;

public class Task extends Assignment {

    private List<Subtask> subtasks;

    public Task() { }


    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public double getSubtasksPrice() {
        double sum = 0.0;
        if (subtasks != null) {
            for (Subtask subtask : subtasks) {
                sum += subtask.getPrice();
            }
        }
        return sum;
    }

    public double getTaskTotalPrice() {
        return getPrice() + getSubtasksPrice();
    }
}