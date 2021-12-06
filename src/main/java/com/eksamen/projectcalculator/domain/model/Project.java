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
    private double projectPrice;

    public Project(long projectId, String projectName, List<Task> tasks, double projectPrice) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.tasks = tasks;
        this.projectPrice = projectPrice;
    }

    public Project() {

    }

    public Project(long projectId, String projectName, List<Task> tasks) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.tasks = tasks;
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

    public double getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(double projectPrice) {
        this.projectPrice = projectPrice;
    }
}
