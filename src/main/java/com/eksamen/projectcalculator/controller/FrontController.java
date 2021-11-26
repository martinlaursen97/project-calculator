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

    @GetMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "index";
        }
        return "login";
    }
}
