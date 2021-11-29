package com.eksamen.projectcalculator.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProjectRepository {
    public void createProject(String projectName) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO project(project_name) VALUES (?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, projectName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
