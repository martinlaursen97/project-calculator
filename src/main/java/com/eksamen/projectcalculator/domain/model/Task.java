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
    private double pricePerHour = 800;
    private double dailyWorkHours = 10;

    public Task(long taskId, long projectId, String taskName, String resource, String startDateStr, String finishDateStr, int percentComplete, List<Subtask> subtasks) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.taskName = taskName;
        this.resource = resource;
        this.startDateStr = startDateStr;
        this.finishDateStr = finishDateStr;
        this.percentComplete = percentComplete;
        this.subtasks = subtasks;
    }

    public Task(long taskId, String taskName, String resource, String startDateStr, String finishDateStr, int percentComplete, List<Subtask> subtasks) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.resource = resource;
        this.startDateStr = startDateStr;
        this.finishDateStr = finishDateStr;
        this.percentComplete = percentComplete;
        this.subtasks = subtasks;
    }

    public Task(long taskId, String taskName, String resource, String startDateStr, String finishDateStr, int percentComplete) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.resource = resource;
        this.startDateStr = startDateStr;
        this.finishDateStr = finishDateStr;
        this.percentComplete = percentComplete;
    }

    public Task() {

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

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", projectId=" + projectId +
                ", taskName='" + taskName + '\'' +
                ", resource='" + resource + '\'' +
                ", startDateStr='" + startDateStr + '\'' +
                ", finishDateStr='" + finishDateStr + '\'' +
                ", percentComplete=" + percentComplete +
                ", subtasks=" + subtasks +
                '}';
    }
}