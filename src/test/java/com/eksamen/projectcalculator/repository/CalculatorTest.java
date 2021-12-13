package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Calculator;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.domain.model.Task;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test // Tæller ikke den sidste dag.
    public void calculator() {

        // Arrange - Calculatoren tæller ikke weekender med som arbejdsdage
        String date1 = "2021 12 10"; // fredag
        String date2 = "2021 12 20"; // mandag
        int expectedWorkDays = 6;

        // Act
        int actualWorkDays = Calculator.getDaysBetweenDates(date1, date2);

        // Assert
        assertEquals(expectedWorkDays, actualWorkDays);
    }

    @Test
    public void Should_CalculatePriceOfTask_When_GivenStartAndFinishDates() {
        // Arrange
        Task task = new Task();
        task.setDailyWorkHours(10);
        task.setPricePerHour(1000);
        task.setStartDateStr("2021 12 10");
        task.setFinishDateStr("2021 12 20");

        double expected = 60000;

        // Act
        double actual = task.getPrice();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void Should_CalculatePriceOfSubtask_When_GivenStartAndFinishDates() {
        // Arrange
        Subtask subtask = new Subtask();
        subtask.setDailyWorkHours(10);
        subtask.setPricePerHour(1000);
        subtask.setStartDateStr("2021 12 10");
        subtask.setFinishDateStr("2021 12 20");

        double expected = 60000;

        // Act
        double actual = subtask.getPrice();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void Should_CalculatePriceOfProject_When_GivenTasksAndSubtasks() {
        // Arrange
        Task task = new Task();
        task.setDailyWorkHours(10);
        task.setPricePerHour(1000);
        // 6  days
        task.setStartDateStr("2021 12 10");
        task.setFinishDateStr("2021 12 20");

        Subtask subtask = new Subtask();
        subtask.setDailyWorkHours(10);
        subtask.setPricePerHour(1000);
        // 6 days
        subtask.setStartDateStr("2021 12 10");
        subtask.setFinishDateStr("2021 12 20");

        List<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask);

        task.setSubtasks(subtasks);

        Project project = new Project();

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        project.setTasks(tasks);

        // 1000 * 10 * 6 * 2
        double expected = 120000;

        // Act
        double actual = project.getTotalProjectPrice();

        // Assert
        assertEquals(expected, actual);
    }
}
