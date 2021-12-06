package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                subtasks.add(subtask);
            }
            return subtasks;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}