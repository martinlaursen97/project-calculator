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
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getBoolean("admin"));
                users.add(user);
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
                user.setAdmin(resultSet.getBoolean("admin"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void changeAdmin(long userId) {
        try {
            Connection connection = DBManager.getConnection();

            String query = "UPDATE user SET admin = NOT admin WHERE user_id = " + userId;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserByKey(String key) {
        try {
            Connection connection = DBManager.getConnection();

            List<User> users = new ArrayList<>();

            String query = "SELECT * FROM user WHERE email LIKE '%" + key + "%' OR user_id = + '" + key + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getBoolean("admin"));
                users.add(user);
            }
            System.out.println(users.size());
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByEmail(String email) {
        try {
            Connection connection = DBManager.getConnection();


            String query = "SELECT * FROM user WHERE email = '" + email + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getBoolean("admin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
