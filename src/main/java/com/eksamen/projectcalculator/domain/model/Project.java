package com.eksamen.projectcalculator.domain.model;

import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Project {

    private Date startDate;
    private Date deadline;
    private String projectName;
    private String projectDescription;
    private List<Task> tasks;
    private List<Project> subprojects;

    public Project (String projectName, String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        tasks = new ArrayList<>();
        subprojects = new ArrayList<>();
    }

    public void addSubproject(Project project){
        subprojects.add(project);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(tasks);
    }

    //Lav unit test
    //samlet tidshorisont for alle tasks
    public double getSumOfTaskHours(){
        int sum = 0;

        for (Task task : tasks) {
            sum += task.getEstimatedHoursInTotal();
        }
        return sum;
    }

    //omkostninger i timen for en task
    public double getSumOfHourlyRates(){
        int sum = 0;

        for (Task task: tasks) {
            sum += task.getSumOfHourlyRate();
        }
        return sum;
    }

    //hvor mange gennemsnitlige daglige arbejdstimer for alle tasks i et projekt
   public double getAvgWorkHoursPrDay() {
       double sum = 0;
        for (int i = 0; i < tasks.size(); i++ ) {
            sum += tasks.get(i).getDailyWorkHours();
        }
        return sum / tasks.size(); //divideret med hvor mange tasks der er
   }

    public void getDeadline() {
        startDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        double endDate = getSumOfTaskHours() / getAvgWorkHoursPrDay();

        calendar.add(Calendar.DATE, (int)endDate);
        Date deadline = calendar.getTime();
        System.out.println(deadline);
        System.out.println(getSumOfTaskHours());
        System.out.println(getAvgWorkHoursPrDay());


    }
}
