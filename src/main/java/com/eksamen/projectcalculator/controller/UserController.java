package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Controller
public class UserController {
    private final UserService USER_SERVICE = new UserService();

    @PostMapping("/verify")
    public String loginVerify(WebRequest request, Model model) {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = USER_SERVICE.loginValid(email, password);
            request.setAttribute("user", user, WebRequest.SCOPE_SESSION);



            ArrayList<Task> tasks = new ArrayList<>();

            Task task1 = new Task(1L, "Rapport-skrivning", "Gruppe", null, null, "2021 11 10", "2021 12 17", 20);
            Task task2 = new Task(2L, "Domæne model", "Gruppe", null, null, "2021 11 25", "2021 11 26", 100);
            Task task3 = new Task(3L, "Virksomhed", "Gruppe", null, null, "2021 11 10", "2021 11 15", 20);

            tasks.add(task1);
            tasks.add(task2);
            tasks.add(task3);


            model.addAttribute("test", tasks);

            return "index";
        } catch (LoginException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
