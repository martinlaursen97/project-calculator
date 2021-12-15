package com.eksamen.projectcalculator.domain.model;

import java.util.List;

public class Project {

    private long projectId;
    private long userId;
    private String projectName;
    private List<Task> tasks;
    private String startDateStr;
    private String deadlineDateStr;



    public Project() {

    }

    public Project(long userId, String projectName) {
        this.userId = userId;
        this.projectName = projectName;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getDeadlineDateStr() {
        return deadlineDateStr;
    }

    public void setDeadlineDateStr(String deadlineDateStr) {
        this.deadlineDateStr = deadlineDateStr;
    }

    public double getTotalProjectPrice() {
        if (tasks == null) return 0.0;

        double sum = 0.0;

        for (Task task : tasks) {
            sum += task.getPrice() + task.getSubtasksPrice();
        }
        return sum;
    }
}
