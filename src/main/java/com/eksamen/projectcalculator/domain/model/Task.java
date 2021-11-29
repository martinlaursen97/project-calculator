package com.eksamen.projectcalculator.domain.model;

import java.util.Date;

public class Task {

    private long taskId;
    private String taskName;
    private String resource;
    private Date start;
    private Date finish;
    private String startStr;
    private String finishStr;
    private int percentComplete;

    public Task(long taskId, String taskName, String resource, Date start, Date finish, String startStr, String finishStr, int percentComplete) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.resource = resource;
        this.start = start;
        this.finish = finish;
        this.startStr = startStr;
        this.finishStr = finishStr;
        this.percentComplete = percentComplete;
    }

    // Getters and Setters
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

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getStartStr() {
        return startStr;
    }

    public void setStartStr(String startStr) {
        this.startStr = startStr;
    }

    public String getFinishStr() {
        return finishStr;
    }

    public void setFinishStr(String finishStr) {
        this.finishStr = finishStr;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }
}
