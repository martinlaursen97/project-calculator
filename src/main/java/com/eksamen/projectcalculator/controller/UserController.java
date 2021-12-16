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

    /**
     * @author Martin
     */

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

    /**
     * @author Jakob, Martin
     */

    @PostMapping("/users/register-verify")
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

    /**
     * @author Jakob
     */

    @GetMapping("/users")
    public String getAdminOverview(Model model, WebRequest request){
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        model.addAttribute("users", USER_SERVICE.getUsers());
        return "adminOverview";
    }

    /**
     * @author Younes
     */

    @GetMapping("/users/user")
    public String showUser(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        //kun en admin kan få vist brugere, derfor skal vi sikre os at brugeren er admin
        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        model.addAttribute("user", USER_SERVICE.getUserById(id));
        return "inspectUser";
    }

    /**
     * @author Martin, Younes
     */

    @GetMapping("/users/user/admin")
    public String changeAdmin(@RequestParam(name= "id") long id, WebRequest request, RedirectAttributes redirectAttributes){
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        // Man skal ikke kunne ændre sin egen admin status
        if (userId == id) {
            return "error";
        }

        USER_SERVICE.changeAdmin(id);
        redirectAttributes.addAttribute("id", id);
        return  "redirect:/users/user";
    }

    /**
     * @author Martin
     */

    @GetMapping("/users/user/delete")
    public String deleteProject(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        model.addAttribute("user", USER_SERVICE.getUserById(id));
        String message;
        if (id != userId) {
            message = "Are you sure? This will delete the user.";
        } else {
            message = "WARNING you are trying to delete your own user!";
        }
        model.addAttribute("message", message);
        return "deleteUser";
    }

    @GetMapping("/users/user/delete-confirm")
    public String deleteProjectConfirm(@RequestParam(name = "id") long id, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        USER_SERVICE.deleteUserById(id);
        return "redirect:/users";
    }

    /**
     * @author Martin, Younes
     */

    @PostMapping("/users/search")
    public String search(WebRequest request, Model model) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin", WebRequest.SCOPE_SESSION);
        if (Boolean.FALSE.equals(isAdmin)) return "redirect:/index";

        String key = request.getParameter("key");
        model.addAttribute("users", USER_SERVICE.getUserByKey(key));
        return "adminOverview";
    }
}
