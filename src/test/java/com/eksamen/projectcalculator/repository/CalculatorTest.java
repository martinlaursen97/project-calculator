package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test // Tæller ikke den sidste dag.
    public void calculator() {
        Calculator calculator = new Calculator();

        // Arrange
        String date1 = "2021 12 10";
        String date2 = "2021 12 20";
        int workDays = 6;

        // Act
        int actualWorkDays = Calculator.getDaysBetweenDates(date1, date2);

        // Assert
        assertEquals(workDays, actualWorkDays);

    }
}
