package com.eksamen.projectcalculator.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRepositoryTest {

    // Should_ExpectedBehavior_When_StateUnderTest

    @Test
    public void Should_FindProject_When_ProjectCreated() {
        ProjectRepository projectRepository = new ProjectRepository();
        projectRepository.createProject("test");
        assertTrue(projectRepository.projectExists("test"));
    }
}
