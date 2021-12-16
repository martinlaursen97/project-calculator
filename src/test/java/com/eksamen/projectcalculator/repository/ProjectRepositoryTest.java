package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRepositoryTest {

    // Should_ExpectedBehavior_When_StateUnderTest
    private final ProjectRepositoryImpl PROJECT_REPOSITORY = new ProjectRepositoryImpl();
    private final UserRepositoryImpl USER_REPOSITORY = new UserRepositoryImpl();


    @Test
    public void Should_ReturnNull_When_RetrievingProjectAfterDelete() {
        // Arrange
        String testEmail = "projectCreateTest@hotmail.dk";

        if (!USER_REPOSITORY.emailExists(testEmail)) {
            User user = new User();
            user.setEmail(testEmail);
            user.setPassword("test");
            user.setAdmin(false);
            USER_REPOSITORY.create(user);
        }

        User user = USER_REPOSITORY.getUserByEmail(testEmail);

        long id = PROJECT_REPOSITORY.create(new Project(user.getUserId(), "test project"));
        Project project = PROJECT_REPOSITORY.read(id);

        boolean expected = project != null;

        // Act
        PROJECT_REPOSITORY.delete(id);
        Project after = PROJECT_REPOSITORY.read(id);

        boolean actual = after == null;

        // Assert
        assertEquals(expected, actual);
    }
}
