package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public long createTask(long projectId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO task(project_id, task_name, resource, start_date, finish_date, percent_complete, daily_work_hours, price_per_hour) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, projectId);
            preparedStatement.setString(2, taskName);
            preparedStatement.setString(3, resource);
            preparedStatement.setString(4, startDate);
            preparedStatement.setString(5, finishDate);
            preparedStatement.setInt(6, completion);
            preparedStatement.setDouble(7, dailyWorkHours);
            preparedStatement.setDouble(8, pricePerHour);
            preparedStatement.executeUpdate();

            ResultSet resultSet  = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectId;
    }

    public List<Task> getTasksByProjectId(long id) {
        try {
            Connection connection = DBManager.getConnection();
            List<Task> tasks = new ArrayList<>();
            String query = "SELECT * FROM task WHERE project_id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getLong("task_id"));
                task.setProjectId(resultSet.getLong("project_id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setResource(resultSet.getString("resource"));

                String startDate = resultSet.getString("start_date");
                String finishDate = resultSet.getString("finish_date");

                task.setStartDateStr(String.join(" ", startDate.split("-")));
                task.setFinishDateStr(String.join(" ", finishDate.split("-")));

                task.setPercentComplete(resultSet.getInt("percent_complete"));
                task.setDailyWorkHours(resultSet.getDouble("daily_work_hours"));
                task.setPricePerHour(resultSet.getDouble("price_per_hour"));
                tasks.add(task);
            }
            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearTasksByProjectId(long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM task WHERE project_id = " + id;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getTaskById(long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM task WHERE task_id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getLong("task_id"));
                task.setProjectId(resultSet.getLong("project_id"));
                task.setTaskName(resultSet.getString("task_name"));
                task.setResource(resultSet.getString("resource"));

                String startDate = resultSet.getString("start_date");
                String finishDate = resultSet.getString("finish_date");

                task.setStartDateStr(String.join(" ", startDate.split("-")));
                task.setFinishDateStr(String.join(" ", finishDate.split("-")));

                task.setPercentComplete(resultSet.getInt("percent_complete"));
                task.setDailyWorkHours(resultSet.getDouble("daily_work_hours"));
                task.setPricePerHour(resultSet.getDouble("price_per_hour"));

                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getProjectIdByTaskId(long taskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT project_id FROM task WHERE task_id = " + taskId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteTaskById(long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM task WHERE task_id = " + id;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getProjectStartDateById(long projectId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT start_date FROM task WHERE project_id = " + projectId + " ORDER BY start_date";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getProjectDeadlineById(long projectId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT finish_date FROM task WHERE project_id = " + projectId + " ORDER BY finish_date";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTaskPercentById(long taskId, int percent) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "UPDATE task SET percent_complete = " + percent + " WHERE task_id  = " + taskId;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
