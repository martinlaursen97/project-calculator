package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.exception.LoginException;
import com.eksamen.projectcalculator.domain.exception.LoginSampleException;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            request.setAttribute("isAdmin", user.isAdmin(), WebRequest.SCOPE_SESSION);
            return "redirect:/index";
        } catch (LoginException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/register-verify")
    public String registerVerify(WebRequest request, Model model) {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String admin = request.getParameter("admin");
            boolean isAdmin = (admin != null);

            if (password.equals(password2)) {
                USER_SERVICE.createUser(email, password, isAdmin);
                return "redirect:/users";
            } else {
                throw new LoginSampleException("Passwords did not match!");
            }
        } catch (LoginException | LoginSampleException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }

    @GetMapping("/users")
    public String getAdminOverview(Model model, WebRequest request){
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "index";

        model.addAttribute("users", USER_SERVICE.getUsers());
        return "adminOverview";
    }

    @GetMapping("/showUser")
    public String showUser(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        model.addAttribute("user", USER_SERVICE.getUserById(id));
        return "inspectUser";
    }
}
