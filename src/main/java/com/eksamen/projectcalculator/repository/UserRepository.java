package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                user.setAdmin(resultSet.getBoolean(4));
                return user;
            } else {
                throw new LoginException("Incorrect details");
            }
        } catch (SQLException e) {
            throw new LoginException("Incorrect details");
        }
    }

    public Long createUser(String email, String password, boolean isAdmin) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO user(email, password, admin) VALUES (?,?,?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setBoolean(3, isAdmin);
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

    public boolean emailExists(String email) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "SELECT * FROM user WHERE email = '" + email + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {

        try {
            Connection connection = DBManager.getConnection();

            List<User> users = new ArrayList<>();

            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User aUser = new User();
                aUser.setUserId(resultSet.getLong("user_id"));
                aUser.setEmail(resultSet.getString("email"));
                aUser.setAdmin(resultSet.getBoolean("admin"));
                users.add(aUser);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserByEmail(String email) {
        try {
            Connection connection = DBManager.getConnection();

            List<User> users = new ArrayList<>();

            String query = "SELECT * FROM user WHERE email = '" + email + "'";//LIKE '%" + email + "%'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User aUser = new User();
                aUser.setUserId(resultSet.getLong("user_id"));
                aUser.setEmail(resultSet.getString("email"));
                aUser.setAdmin(resultSet.getBoolean("admin"));
                users.add(aUser);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(long id) {
        try {
            Connection connection = DBManager.getConnection();

            String query = "SELECT * FROM user WHERE user_id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
