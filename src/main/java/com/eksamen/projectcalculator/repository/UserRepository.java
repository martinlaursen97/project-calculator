package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.CRUDRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements CRUDRepository<User> {

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

    @Override
    public long create(User user) {
        try {
            Connection connection = DBManager.getConnection();
            String query = "INSERT INTO user(email, password, admin) VALUES (?,?,?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBoolean(3, user.isAdmin());
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
    public User read(long id) {
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

    @Override
    public void update(long objId, double val) {

    }

    @Override
    public void update(long userId) {
        try {
            Connection connection = DBManager.getConnection();

            String query = "UPDATE user SET admin = NOT admin WHERE user_id = " + userId;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long objId) {

    }

    @Override
    public List<User> getAllById(long userId) {
        return null;
    }

    @Override
    public List<User> getAll() {

        try {
            Connection connection = DBManager.getConnection();

            List<User> users = new ArrayList<>();

            String query = "SELECT * FROM user ORDER BY user_id DESC";
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
