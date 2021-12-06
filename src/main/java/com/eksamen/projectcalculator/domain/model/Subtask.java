package com.eksamen.projectcalculator.domain.model;

import java.util.List;

public class Subtask {

    private long subtaskId;
    private long taskId;
    private String subtaskName;
    private String resource;
    private String startDateStr; // til javascript
    private String finishDateStr;
    private int percentComplete;

    public Subtask(long subtaskId, long taskId, String subtaskName, String resource, String startDateStr, String finishDateStr, int percentComplete) {
        this.subtaskId = subtaskId;
        this.taskId = taskId;
        this.subtaskName = subtaskName;
        this.resource = resource;
        this.startDateStr = startDateStr;
        this.finishDateStr = finishDateStr;
        this.percentComplete = percentComplete;
    }

    public Subtask() {

    }

    public long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getSubtaskName() {
        return subtaskName;
    }

    public void setSubtaskName(String subtaskName) {
        this.subtaskName = subtaskName;
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

    @Override
    public String toString() {
        return "Subtask{" +
                "subtaskId=" + subtaskId +
                ", taskId=" + taskId +
                ", subtaskName='" + subtaskName + '\'' +
                ", resource='" + resource + '\'' +
                ", startDateStr='" + startDateStr + '\'' +
                ", finishDateStr='" + finishDateStr + '\'' +
                ", percentComplete=" + percentComplete +
                '}';
    }
}
