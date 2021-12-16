package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    @Override
    public long create(Project project) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO project(user_id, project_name) VALUES (?, ?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, project.getUserId());
            preparedStatement.setString(2, project.getProjectName());
            preparedStatement.executeUpdate();

            ResultSet resultSet  = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Project read(long projectId) {
        try {
            Connection connection = DBManager.getConnection();

            String query = "SELECT * FROM project WHERE project_id = " + projectId;

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

    @Override
    public void update(Project val) {

    }


    @Override
    public void delete(long projectId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM project WHERE project_id = " + projectId;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getProjectsByUserId(long userId) {
        try {
            Connection connection = DBManager.getConnection();
            List<Project> projects = new ArrayList<>();
            String query = "SELECT * FROM project WHERE user_id = " + userId + " ORDER BY project_id DESC";

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

    @Override
    public boolean projectIsUsers(long userId, long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM project WHERE project_id = " + id + " AND user_id = " + userId;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
