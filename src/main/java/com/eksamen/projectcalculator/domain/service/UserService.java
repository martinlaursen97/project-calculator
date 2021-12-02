package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.repository.DataFacade;

public class UserService {

    private final DataFacade FACADE = new DataFacade();

    public User loginValid(String email, String password) throws LoginException {
        return FACADE.loginValid(email, password);
    }

    public void createUser(String email, String password, boolean isAdmin) throws LoginException {
        FACADE.createUser(email, password, isAdmin);
    }
}
