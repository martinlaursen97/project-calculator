package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    @Override
    public long create(Task task) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO task(project_id, task_name, resource, start_date, finish_date, percent_complete, daily_work_hours, price_per_hour) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, task.getForeignId());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getResource());
            preparedStatement.setString(4, task.getStartDateStr());
            preparedStatement.setString(5, task.getFinishDateStr());
            preparedStatement.setInt(6, task.getPercentComplete());
            preparedStatement.setDouble(7, task.getDailyWorkHours());
            preparedStatement.setDouble(8, task.getPricePerHour());
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
    public Task read(long taskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM task WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("task_id"));
                task.setForeignId(resultSet.getLong("project_id"));
                task.setName(resultSet.getString("task_name"));
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

    @Override
    public void update(Task task) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "UPDATE task SET percent_complete = ? WHERE task_id  = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, task.getPercentComplete());
            preparedStatement.setLong(2, task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long taskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM task WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getTasksByProjectId(long projectId) {
        try {
            Connection connection = DBManager.getConnection();
            List<Task> tasks = new ArrayList<>();
            String query = "SELECT * FROM task WHERE project_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("task_id"));
                task.setForeignId(resultSet.getLong("project_id"));
                task.setName(resultSet.getString("task_name"));
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

    @Override
    public void clearTasksByProjectId(long id) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM task WHERE project_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getProjectIdByTaskId(long taskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT project_id FROM task WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public String getProjectStartDateById(long projectId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "(SELECT start_date FROM task WHERE project_id = ?) " +
                    "UNION " +
                    "(SELECT s.start_date FROM subtask s INNER JOIN task t ON s.task_id = t.task_id WHERE t.project_id = ?) " +
                    "ORDER BY start_date ASC;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, projectId);
            preparedStatement.setLong(2, projectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getProjectDeadlineById(long projectId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "(SELECT finish_date FROM task WHERE project_id = ?) " +
                    "UNION " +
                    "(SELECT s.finish_date FROM subtask s INNER JOIN task t ON s.task_id = t.task_id WHERE t.project_id = ?) " +
                    "ORDER BY finish_date DESC;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, projectId);
            preparedStatement.setLong(2, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
