package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    public void createProject(long id, String projectName) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO project(user_id, project_name) VALUES (?, ?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, projectName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean projectExists(String projectName) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM project WHERE project_name = '" + projectName + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ignore) {

        }
        return false;
    }

    public List<Project> getProjectsByUserId(long userId) {
        try {
            Connection connection = DBManager.getConnection();
            List<Project> projects = new ArrayList<>();
            String query = "SELECT * FROM project WHERE user_id = " + userId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectName(resultSet.getString("project_name"));
                project.setProjectId(resultSet.getLong("project_id"));
                projects.add(project);
            }
            return projects;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Project getProjectById(long id) {
        try {
            Connection connection = DBManager.getConnection();

            String query = "SELECT * FROM project WHERE project_id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Project project = new Project();
                project.setProjectId(resultSet.getLong("project_id"));
                project.setProjectName(resultSet.getString("project_name"));
                return project;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean projectIsUsers(long userId, long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM project WHERE project_id = " + id + " AND user_id = " + userId;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException ignore) {

        }
        return false;
    }


    public void deleteProjectById(long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM project WHERE project_id = " + id;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
