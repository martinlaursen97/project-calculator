package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ProjectController {

    private final ProjectService PROJECT_SERVICE = new ProjectService();

    //viser ens projekter
    @GetMapping("/projects")
    public String projects(WebRequest request, Model model) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        model.addAttribute("projects", PROJECT_SERVICE.getProjectsByUserId(userId));

        return "projects";
    }

    @PostMapping("/add-verify")
    public String createProjectVerify(WebRequest request, Model model) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        try {
            // Brugeren indtaster projekt navnet, hvilket bliver hentet ud af en HTML form via WebRequest,
            // og gemt i databasen med mindre at ProjectException opstår
            String projectName = request.getParameter("name");
            PROJECT_SERVICE.createProject(userId, projectName);
        } catch (ProjectException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/projects";
    }

    @GetMapping("/project")
    public String showProject(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        // Det er vigtigt at en bruger kun har adgang til sine egne projekter/tasks/subtasks,
        // derfor tjekkes der om projektet tilhører brugeren FØR den fortsætter
        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            Project project = PROJECT_SERVICE.getProjectById(id);
            model.addAttribute("project", project);
            return "inspectProject";
        } else {
            return "error";
        }
    }

    // Sletter alle tasks i et projekt
    @GetMapping("/project/clear")
    public String clear(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        model.addAttribute("project", PROJECT_SERVICE.getProjectById(id));
        model.addAttribute("message", "Are you sure? This will remove all tasks.");
        return "clearTasks";
    }

    @GetMapping("/project/delete")
    public String deleteProject(@RequestParam(name = "id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            model.addAttribute("project", PROJECT_SERVICE.getProjectById(id));
            model.addAttribute("message", "Are you sure? This will delete the project.");
            return "deleteProject";
        } else {
            return "error";
        }
    }

    @GetMapping("/project/delete-confirm")
    public String deleteProjectConfirm(@RequestParam(name = "id") long id, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            PROJECT_SERVICE.deleteProjectById(id);
            return "redirect:/projects";
        } else {
            return "error";
        }
    }
}
