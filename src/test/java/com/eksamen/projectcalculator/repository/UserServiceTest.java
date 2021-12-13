package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    private final UserService USER_SERVICE = new UserService();

    @Test
    public void Should_ThrowLoginException_When_UserNotFound() {
        assertThrows(LoginException.class, () -> USER_SERVICE.loginValid("notInDb@hotmail.dk", "123321"));
    }

    @Test
    public void asd() {
        // Arrange

        // Act
        // Assert
    }
}

