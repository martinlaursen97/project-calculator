package com.eksamen.projectcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/index")
    public String index(WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        return "index";
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping("/register")
    public String registerUser(WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        return "register";
    }
}
