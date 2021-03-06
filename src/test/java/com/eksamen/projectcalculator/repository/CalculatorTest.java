package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.service.Calculator;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.domain.model.Task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jakob, Martin
 */

/*
    Environment variabler med url, user, password, som passer til din database,
    skal muligvis sættes til test klasserne, før integrationstests vil fungere.
*/


// Should_ExpectedBehavior_When_StateUnderTest
public class CalculatorTest {

    @Test
    public void Should_ReturnNumberOfWorkDaysBetweenDates_When_GivenTwoDates() {

        // Arrange - Calculatoren tæller ikke weekender med som arbejdsdage
        String date1 = "2021 12 10"; // lørdag
        String date2 = "2021 12 20"; // søndag
        int expectedWorkDays = 7;

        // Act
        int actualWorkDays = Calculator.getWorkDaysBetweenDates(date1, date2);

        // Assert
        assertEquals(expectedWorkDays, actualWorkDays);
    }

    @Test
    public void Should_CalculatePriceOfTask_When_GivenStartAndFinishDates() {
        // Arrange
        Task task = new Task();
        task.setDailyWorkHours(5);
        task.setPricePerHour(150);
        task.setStartDateStr("2021 12 18");
        task.setFinishDateStr("2021 12 19");

        double expected = 0;

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

        double expected = 70000;

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
        // 7  days
        task.setStartDateStr("2021 12 10");
        task.setFinishDateStr("2021 12 20");

        Subtask subtask = new Subtask();
        subtask.setDailyWorkHours(10);
        subtask.setPricePerHour(1000);
        // 7 days
        subtask.setStartDateStr("2021 12 10");
        subtask.setFinishDateStr("2021 12 20");

        List<Subtask> subtasks = new ArrayList<>();
        subtasks.add(subtask);

        task.setSubtasks(subtasks);

        Project project = new Project();

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        project.setTasks(tasks);

        // 1000 * 10 * 7 * 2
        double expected = 140000;

        // Act
        double actual = project.getTotalProjectPrice();

        // Assert
        assertEquals(expected, actual);
    }
}
