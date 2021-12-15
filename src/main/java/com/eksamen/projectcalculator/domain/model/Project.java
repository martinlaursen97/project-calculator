package com.eksamen.projectcalculator.domain.model;

import java.util.List;

public class Project {

    private long projectId;
    private String projectName;
    private List<Task> tasks;
    private String startDateStr;
    private String deadlineDateStr;

    public Project(long projectId, String projectName, List<Task> tasks, String startDateStr, String deadlineDateStr) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.tasks = tasks;
        this.startDateStr = startDateStr;
        this.deadlineDateStr = deadlineDateStr;
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
