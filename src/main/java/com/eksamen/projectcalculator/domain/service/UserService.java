package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.exception.LoginSampleException;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.repository.DataFacade;

import java.util.List;

public class UserService {

    private final DataFacade FACADE = new DataFacade();

    public User loginValid(String email, String password) throws LoginException {
        return FACADE.loginValid(email, password);
    }

    public long createUser(String email, String password, boolean isAdmin) throws LoginException {
        return FACADE.createUser(email, password, isAdmin);
    }

    public List<User> getUsers() {
        return FACADE.getUsers();
    }


    public User getUserById(long id) {
        return FACADE.getUserById(id);
    }


    public void changeAdmin(Long userId) {
        FACADE.changeAdmin(userId);
    }

    public List<User> getUserByKey(String key) {
        return FACADE.getUserByKey(key);
    }
}
