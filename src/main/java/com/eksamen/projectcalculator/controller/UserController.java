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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService USER_SERVICE = new UserService();

    @PostMapping("/verify")
    public String loginVerify(WebRequest request, Model model) {
        // LoginException kastes hvis email og password, ikke matcher en bruger i databasen.
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            User user = USER_SERVICE.loginValid(email, password);

            // Session attributter gemmes, og kan bruges over alt i programmet.
            // Mest til at sikre at der kun gives adgang til brugere.
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
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        // Det er kun en admin der skal have adgang til registrerings siden,
        // derfor tjekkes isAdmin session attributten  for true eller false
        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String admin = request.getParameter("admin");

            // En admin vælger om en ny bruger er admin eller ej med en checkbox.
            // Hvis checkboxen er krydset af, vil WebRequest hente en string værdi ud, og hvis ikke, er den null.
            boolean isAdmin2 = (admin != null);

            // Adminstratoren skal skrive password to gange, for at sikre at han har tastet rigtigt.
            if (password.equals(password2)) {
                USER_SERVICE.createUser(email, password, isAdmin2);
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
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        model.addAttribute("users", USER_SERVICE.getUsers());
        return "adminOverview";
    }

    @GetMapping("/showUser")
    public String showUser(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        model.addAttribute("user", USER_SERVICE.getUserById(id));
        return "inspectUser";
    }

    @GetMapping("/changeAdmin")
    public String changeAdmin(@RequestParam(name= "id") long id, WebRequest request, RedirectAttributes redirectAttributes){
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        if (userId == id) {
            request.setAttribute("isAdmin", false, WebRequest.SCOPE_SESSION);
        }

        USER_SERVICE.changeAdmin(id);
        redirectAttributes.addAttribute("id", id);
        return  "redirect:/showUser";
    }
}
