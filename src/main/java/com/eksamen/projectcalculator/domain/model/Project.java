package com.eksamen.projectcalculator.domain.model;

import java.util.ArrayList;

public class Project {
   private String projectName;
   private ArrayList<Task> tasks;

   public Project(String projectName, ArrayList<Task> tasks) {
      this.projectName = projectName;
      this.tasks = tasks;
   }

   public String getProjectName() {
      return projectName;
   }

   public void setProjectName(String projectName) {
      this.projectName = projectName;
   }

   public ArrayList<Task> getTasks() {
      return tasks;
   }

   public void setTasks(ArrayList<Task> tasks) {
      this.tasks = tasks;
   }

   public void addTask(Task task) {
      tasks.add(task);
   }
}

