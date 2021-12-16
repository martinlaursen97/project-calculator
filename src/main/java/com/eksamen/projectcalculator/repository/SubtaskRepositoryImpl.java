package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Subtask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubtaskRepositoryImpl implements SubtaskRepository {

    @Override
    public long create(Subtask subtask) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO subtask(task_id, subtask_name, resource, start_date, finish_date, percent_complete, daily_work_hours, price_per_hour) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, subtask.getId());
            preparedStatement.setString(2, subtask.getName());
            preparedStatement.setString(3, subtask.getResource());
            preparedStatement.setString(4, subtask.getStartDateStr());
            preparedStatement.setString(5, subtask.getFinishDateStr());
            preparedStatement.setInt(6, subtask.getPercentComplete());
            preparedStatement.setDouble(7, subtask.getDailyWorkHours());
            preparedStatement.setDouble(8, subtask.getPricePerHour());
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
    public Subtask read(long subtaskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM subtask WHERE subtask_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, subtaskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Subtask subtask = new Subtask();
                subtask.setId(resultSet.getLong("subtask_id"));
                subtask.setForeignId(resultSet.getLong("task_id"));
                subtask.setName(resultSet.getString("subtask_name"));
                subtask.setResource(resultSet.getString("resource"));

                String startDate = resultSet.getString("start_date");
                String finishDate = resultSet.getString("finish_date");

                subtask.setStartDateStr(String.join(" ", startDate.split("-")));
                subtask.setFinishDateStr(String.join(" ", finishDate.split("-")));

                subtask.setPercentComplete(resultSet.getInt("percent_complete"));
                subtask.setDailyWorkHours(resultSet.getDouble("daily_work_hours"));
                subtask.setPricePerHour(resultSet.getDouble("price_per_hour"));

                return subtask;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Subtask subtask) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "UPDATE subtask SET percent_complete = ? WHERE subtask_id  = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, subtask.getPercentComplete());
            preparedStatement.setLong(2, subtask.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long subtaskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM subtask WHERE subtask_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, subtaskId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Subtask> getSubtasksByTaskId(long taskId) {
        try {
            Connection connection = DBManager.getConnection();
            List<Subtask> subtasks = new ArrayList<>();
            String query = "SELECT * FROM subtask WHERE task_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Subtask subtask = new Subtask();
                subtask.setId(resultSet.getLong("subtask_id"));
                subtask.setForeignId(resultSet.getLong("task_id"));
                subtask.setName(resultSet.getString("subtask_name"));
                subtask.setResource(resultSet.getString("resource"));

                String startDate = resultSet.getString("start_date");
                String finishDate = resultSet.getString("finish_date");

                subtask.setStartDateStr(String.join(" ", startDate.split("-")));
                subtask.setFinishDateStr(String.join(" ", finishDate.split("-")));

                subtask.setPercentComplete(resultSet.getInt("percent_complete"));
                subtask.setDailyWorkHours(resultSet.getDouble("daily_work_hours"));
                subtask.setPricePerHour(resultSet.getDouble("price_per_hour"));

                subtasks.add(subtask);
            }
            return subtasks;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getTaskIdBySubtaskId(long subtaskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT task_id FROM subtask WHERE subtask_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, subtaskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}