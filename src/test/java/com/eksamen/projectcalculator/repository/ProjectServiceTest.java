package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.ProjectService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest {

    // Should_ExpectedBehavior_When_StateUnderTest

    private final ProjectService PROJECT_SERVICE = new ProjectService();

    // Bliver brugt til at lave enkelte test users, til at tildele projekter
    private final UserRepository USER_REPOSITORY = new UserRepository();


    @Test
    public void Should_AddProjectToDb_When_ProjectCreated() throws ProjectException {
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

        long expected = PROJECT_SERVICE.createProject(user.getUserId(), "test project");

        // Act
        Project project = PROJECT_SERVICE.getProjectById(expected);
        long actual = project.getProjectId();

        // Assert
        assertEquals(actual, expected);
    }
}
