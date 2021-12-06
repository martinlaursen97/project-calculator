package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Subtask;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.model.User;
import com.eksamen.projectcalculator.domain.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ProjectController {

    private final ProjectService PROJECT_SERVICE = new ProjectService();

    @GetMapping("/projects")
    public String projects(WebRequest request, Model model) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);

        if (userId == null) return "login";

        model.addAttribute("projects", PROJECT_SERVICE.getProjectsByUserId(userId));
        return "projects";
    }

    @PostMapping("/addVerify")
    public String createProjectVerify(WebRequest request, Model model) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);

        if (userId == null) return "login";


        try {
            String projectName = request.getParameter("name");
            PROJECT_SERVICE.createProject(userId, projectName);
        } catch (ProjectException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/projects";
    }

    @GetMapping("/project")
    public String showProject(@RequestParam (name="id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);

        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            Project project = PROJECT_SERVICE.getProjectById(id);
            model.addAttribute("project", project);
            return "inspectProject";
        } else {
            return "error";
        }
    }
}
