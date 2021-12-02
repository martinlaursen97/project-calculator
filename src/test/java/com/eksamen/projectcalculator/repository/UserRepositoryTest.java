package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    // Should_ExpectedBehavior_When_StateUnderTest

    @Test
    public void Should_ThrowLoginException_When_UserNotFound() {
        UserRepository userRepository = new UserRepository();
        assertThrows(LoginException.class, () -> userRepository.loginValid("notInDb@hotmail.dk", "123321"));
    }
}
