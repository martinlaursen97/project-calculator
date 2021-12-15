package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.User;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    private final UserRepository USER_REPOSITORY = new UserRepository();

    // Should_ExpectedBehavior_When_StateUnderTest

    // Finder bruger og kalder på changeAdmin, finder brugeren igen og ser om isAdmin har ændret sig
    @Test
        public void Should_SwapAdminTrueFalse_When_AdminToggled() {
        // Arrange
        String testEmail = "adminToggleTest@hotmail.dk";

        if (!USER_REPOSITORY.emailExists(testEmail)) {
            User user = new User();
            user.setEmail(testEmail);
            user.setPassword("test");
            user.setAdmin(false);
            USER_REPOSITORY.create(user);
        }

        User before = USER_REPOSITORY.getUserByEmail(testEmail);
        boolean expected = before.isAdmin();

        // Act
        USER_REPOSITORY.update(before.getUserId());
        User after = USER_REPOSITORY.getUserByEmail(testEmail);

        boolean actual = after.isAdmin();

        // Assert
        assertNotEquals(expected, actual);
    }
}
