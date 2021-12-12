package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Subtask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubtaskRepository {
    public List<Subtask> getSubtasksByTaskId(long taskId) {
        try {
            Connection connection = DBManager.getConnection();
            List<Subtask> subtasks = new ArrayList<>();
            String query = "SELECT * FROM subtask WHERE task_id = " + taskId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Subtask subtask = new Subtask();
                subtask.setSubtaskId(resultSet.getLong("subtask_id"));
                subtask.setTaskId(resultSet.getLong("task_id"));
                subtask.setSubtaskName(resultSet.getString("subtask_name"));
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

    public Long createSubtask(long taskId, String taskName, String resource, String startDate, String finishDate, int completion, double dailyWorkHours, double pricePerHour) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO subtask(task_id, subtask_name, resource, start_date, finish_date, percent_complete, daily_work_hours, price_per_hour) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, taskId);
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
        return null;
    }

    public long getTaskIdBySubtaskId(long subtaskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT task_id FROM subtask WHERE subtask_id = " + subtaskId;

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

    public Subtask getSubtaskById(long subtaskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM subtask WHERE subtask_id = " + subtaskId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
                Subtask subtask = new Subtask();
                subtask.setSubtaskId(resultSet.getLong("subtask_id"));
                subtask.setTaskId(resultSet.getLong("task_id"));
                subtask.setSubtaskName(resultSet.getString("subtask_name"));
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

    public void deleteSubtaskById(long subtaskId) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "DELETE FROM subtask WHERE subtask_id = " + subtaskId;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSubtaskPercentById(long subtaskId, int percent) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "UPDATE subtask SET percent_complete = " + percent + " WHERE subtask_id  = " + subtaskId;
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}