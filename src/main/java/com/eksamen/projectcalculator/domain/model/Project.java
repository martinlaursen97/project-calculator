package com.eksamen.projectcalculator.domain.model;

import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Project {

    private long projectId;
    private String projectName;
    private List<Task> tasks;

    public Project(long projectId, String projectName, List<Task> tasks) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.tasks = tasks;

    }

    public Project() {

    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public double getTotalProjectPrice() {
        if (tasks == null) return 0.0;

        double sum = 0.0;

        for (Task task : tasks) {
            sum += Calculator.getDaysBetweenDates(task.getStartDateStr(), task.getFinishDateStr()) * task.getDailyWorkHours() * task.getPricePerHour();
            if (task.getSubtasks() != null) {
                for (Subtask subtask : task.getSubtasks()) {
                    sum += Calculator.getDaysBetweenDates(subtask.getStartDateStr(), subtask.getFinishDateStr()) * subtask.getDailyWorkHours() * subtask.getPricePerHour();

                }
            }
        }

        return sum;
    }
}
