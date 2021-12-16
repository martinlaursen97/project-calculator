package com.eksamen.projectcalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    /*
    Når en bruger logger ind, bliver user id og admin status gemt som session attributter,
    som vi bruger til at sikre at det kun er alpha solutions brugere der har adgang til siden,
    samt at det kun er adminstratorere, der har adgang til de adminstrative funktioner.
    */

    /**
     * @author Jakob
     */

    @GetMapping("/index")
    public String index(WebRequest request) {
        // Hvis userId er null, er der ikke blevet sat noget userId som session attribut.
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        return "index";
    }

    /**
     * @author Martin
     */

    @GetMapping("/")
    public String login() {
        return "login";
    }

    /**
     * @author Martin
     */

    // Session attributter invalideres / fjernes, og brugeren er ikke længere logget på
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    /**
     * @author Martin, Younes
     */

    @GetMapping("/users/register")
    public String registerUser(WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        return "register";
    }
}
