package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.repository.DataFacade;

import java.util.List;

public class UserService {

    private final DataFacade FACADE = new DataFacade();

    public User loginValid(String email, String password) throws LoginException {
        return FACADE.loginValid(email, password);
    }

    public void createUser(String email, String password, boolean isAdmin) throws LoginException {
        FACADE.createUser(email, password, isAdmin);
    }

    public List<User> getUsers() {
        return FACADE.getUsers();
    }

    public List<User> getUserByEmail(String email) {
        return FACADE.getUserByEmail(email);
    }

    public User getUserById(long id) {
        return FACADE.getUserById(id);
    }
}
