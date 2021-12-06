package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class UserController {
    private final UserService USER_SERVICE = new UserService();

    @PostMapping("/verify")
    public String loginVerify(WebRequest request, Model model) {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = USER_SERVICE.loginValid(email, password);
            request.setAttribute("userId", user.getUserId(), WebRequest.SCOPE_SESSION);
            return "redirect:/index";
        } catch (LoginException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/registerVerify")
    public String registerVerify(WebRequest request, Model model) {

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String admin = request.getParameter("admin");
            boolean isAdmin = admin != null;

            USER_SERVICE.createUser(email, password, isAdmin);
            return "redirect:/users";
        } catch (LoginException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }

    }
}
