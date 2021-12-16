package com.eksamen.projectcalculator.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {

    // Should_ExpectedBehavior_When_StateUnderTest

    // Singletons burde kun lave en enkelt instans af en klasse
    @Test
    public void Should_GiveSingleInstance_When_CreatingDatafacadeTwice() {
        DataFacade f1 = DataFacade.getInstance();
        DataFacade f2 = DataFacade.getInstance();
        assertEquals(f1, f2);
    }

    // Virker kun hvis environment variabler er opsat i test klassen
    @Test
    public void Should_GiveSingleInstance_When_CreatingConnectionTwice() {
        Connection c1 = DBManager.getConnection();
        Connection c2 = DBManager.getConnection();
        assertEquals(c1, c2);
    }


}
