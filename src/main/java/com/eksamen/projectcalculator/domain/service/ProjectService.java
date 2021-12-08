package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.repository.DataFacade;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProjectService {

    private final DataFacade FACADE = new DataFacade();

    public void createProject(long id, String projectName) throws ProjectException {
        if (projectName.length() > 0 && projectName.length() <= 50) {
            FACADE.createProject(id, projectName);
        } else {
            throw new ProjectException("Invalid input");
        }
    }

    public List<Project> getProjectsByUserId(long userId) {
        return FACADE.getProjectsByUserId(userId);
    }

    public Project getProjectById(long id) {
        Project project = FACADE.getProjectById(id);
        if (project.getTasks() == null) project.setProjectPrice(0.0);

        double sum = 0;
        for (Task task : project.getTasks()) {
            sum += (calculator(task.getStartDateStr(), task.getFinishDateStr()) * task.getDailyWorkHours()) * task.getPricePerHour();
            if (task.getSubtasks() != null) {
                for (Subtask subtask : task.getSubtasks()) {
                    sum += (calculator(subtask.getStartDateStr(), subtask.getFinishDateStr()) * subtask.getDailyWorkHours()) * subtask.getPricePerHour();

                }
            }

        }
        project.setProjectPrice(sum);
        System.out.println(sum);
        return project;
    }

    public int calculator(String start, String finish) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy MM dd");
            Date date1 = df.parse(start);
            Date date2 = df.parse(finish);
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);

            int numberOfDays = 0;
            while (cal1.before(cal2)) {
                if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                        && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                    numberOfDays++;
                }
                cal1.add(Calendar.DATE, 1);
            }
            return numberOfDays;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean projectIsUsers(long userId, long id) {
        return FACADE.projectIsUsers(userId, id);
    }

    public void deleteProjectById(long id) {
        FACADE.deleteProjectById(id);
    }
}
