package com.eksamen.projectcalculator.domain.model;

public class Task {

    private long taskId;
    private String taskName;
    private String taskDescription;
    private double sumOfHourlyRate;
    private double dailyWorkHours;
    private double estimatedHoursInTotal;

    public Task(long taskId, String taskName, String taskDescription, double sumOfHourlyRate, double dailyWorkHours, double estimatedHoursInTotal) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.sumOfHourlyRate = sumOfHourlyRate;
        this.dailyWorkHours = dailyWorkHours;
        this.estimatedHoursInTotal = estimatedHoursInTotal;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public double getSumOfHourlyRate() {
        return sumOfHourlyRate;
    }

    public void setSumOfHourlyRate(double sumOfHourlyRate) {
        this.sumOfHourlyRate = sumOfHourlyRate;
    }

    public double getDailyWorkHours() {
        return dailyWorkHours;
    }

    public void setDailyWorkHours(double dailyWorkHours) {
        this.dailyWorkHours = dailyWorkHours;
    }

    public double getEstimatedHoursInTotal() {
        return estimatedHoursInTotal;
    }

    public void setEstimatedHoursInTotal(double estimatedHoursInTotal) {
        this.estimatedHoursInTotal = estimatedHoursInTotal;
    }
}
