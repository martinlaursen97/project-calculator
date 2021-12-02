package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    public User loginValid(String email, String password) throws LoginException {
        try {
            User user = new User();
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = " + password;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                return user;
            } else {
                throw new LoginException("Incorrect details");
            }
        } catch (SQLException e) {
            throw new LoginException("Incorrect details");
        }
    }

    public void createUser(String email, String password, boolean isAdmin) throws LoginException {
        try {
            if (emailExists(email)) {
                throw new LoginException("Email taken");

            }
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO user(email, password, admin) VALUES (?,?,?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setBoolean(3, isAdmin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new LoginException("Email taken");
        }
    }

    private boolean emailExists(String email) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM user WHERE email = '" + email + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
