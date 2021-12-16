package com.eksamen.projectcalculator.repository;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.User;

import java.util.List;

public interface UserRepository extends CRUDRepository<User> {
    User loginValid(String email, String password) throws LoginException;
    List<User> getAll();
    boolean emailExists(String email);
    List<User> getUserByKey(String key);
    User getUserByEmail(String email);
}
