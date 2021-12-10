package com.eksamen.projectcalculator.domain.model;

import java.util.Date;
import java.util.List;

public class Task {

    private long taskId;
    private long projectId;
    private String taskName;
    private String resource;
    private String startDateStr; // til javascript
    private String finishDateStr;
    private int percentComplete;
    private List<Subtask> subtasks;
    private double dailyWorkHours;
    private double pricePerHour;

    public Task(long taskId, long projectId, String taskName, String resource, String startDateStr, String finishDateStr, int percentComplete, List<Subtask> subtasks, double dailyWorkHours, double pricePerHour) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.taskName = taskName;
        this.resource = resource;
        this.startDateStr = startDateStr;
        this.finishDateStr = finishDateStr;
        this.percentComplete = percentComplete;
        this.subtasks = subtasks;
        this.dailyWorkHours = dailyWorkHours;
        this.pricePerHour = pricePerHour;
    }

    public Task() {

    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getFinishDateStr() {
        return finishDateStr;
    }

    public void setFinishDateStr(String finishDateStr) {
        this.finishDateStr = finishDateStr;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public double getDailyWorkHours() {
        return dailyWorkHours;
    }

    public void setDailyWorkHours(double dailyWorkHours) {
        this.dailyWorkHours = dailyWorkHours;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public double getPrice() {
        return Calculator.getDaysBetweenDates(startDateStr, finishDateStr) * dailyWorkHours * pricePerHour;
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