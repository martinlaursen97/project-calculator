package com.eksamen.projectcalculator.domain.service;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.repository.DataFacade;
import java.util.List;

public class UserService {

    private final DataFacade FACADE = DataFacade.getInstance();

    public User loginValid(String email, String password) throws LoginException {
        return FACADE.loginValid(email, password);
    }

    public long createUser(String email, String password, boolean isAdmin) throws LoginException {
        if (!FACADE.emailExists(email)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setAdmin(isAdmin);
            return FACADE.createUser(user);
        } else {
            throw new LoginException("Email taken");
        }
    }

    public List<User> getUsers() {
        return FACADE.getUsers();
    }


    public User getUserById(long id) {
        return FACADE.getUserById(id);
    }


    public void changeAdmin(Long userId) {
        User user = new User();
        user.setUserId(userId);
        FACADE.changeAdmin(user);
    }

    public List<User> getUserByKey(String key) {
        return FACADE.getUserByKey(key);
    }

    public void deleteUserById(long id) {
        FACADE.deleteUserById(id);
    }
}
