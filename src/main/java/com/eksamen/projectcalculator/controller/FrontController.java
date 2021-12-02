package com.eksamen.projectcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class FrontController {

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }
}


