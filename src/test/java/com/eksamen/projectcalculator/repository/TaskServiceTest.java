package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.InvalidDateException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.TaskService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private final UserRepositoryImpl USER_REPOSITORY = new UserRepositoryImpl();
    private final ProjectRepositoryImpl PROJECT_REPOSITORY = new ProjectRepositoryImpl();

    private final TaskService TASK_SERVICE = new TaskService();
    // Should_ExpectedBehavior_When_StateUnderTest

    // Opretter task i database, og ser om den kan findes igen.
    @Test
    public void Should_AddNewTaskToDb_When_CreateTask() throws InvalidDateException {
        // Arrange
        String testEmail = "adminToggleTest@hotmail.dk";

        if (!USER_REPOSITORY.emailExists(testEmail)) {
            User user = new User();
            user.setEmail(testEmail);
            user.setPassword("test");
            user.setAdmin(false);
            USER_REPOSITORY.create(user);
        }

        User user = USER_REPOSITORY.getUserByEmail(testEmail);

        long id = PROJECT_REPOSITORY.create(new Project(user.getUserId(), "test project"));

        long taskId = TASK_SERVICE.createTask(id, "test", "test", "2021-01-01", "2021-02-02", 1, 1, 1);

        Task task = TASK_SERVICE.getTaskById(taskId);
        boolean expected = task != null;

        // Act
        boolean actual = task == null;

        // Assert
        assertEquals(expected, actual);
    }

}
