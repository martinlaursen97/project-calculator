package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class FrontController {

    @GetMapping("/index")
    public String index(WebRequest request) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        if (user.equals(null)) {
            return "login";
        }
        boolean isAdmin = user.isAdmin();
        System.out.println(user);

        return "index";
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }
}

