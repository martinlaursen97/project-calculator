package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.exception.ProjectException;
import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class ProjectController {

    private final ProjectService PROJECT_SERVICE = new ProjectService();

    @GetMapping("/projects")
    public String projects(WebRequest request, Model model) {
        if (request.getAttribute("user", WebRequest.SCOPE_SESSION) == null) return "login";

        model.addAttribute("projects", PROJECT_SERVICE.getProjects());
        return "projects";
    }

    @PostMapping("/addVerify")
    public String createProjectVerify(WebRequest request, Model model) {
        if (request.getAttribute("user", WebRequest.SCOPE_SESSION) == null) return "login";

        try {
            String projectName = request.getParameter("name");
            PROJECT_SERVICE.createProject(projectName);
        } catch (ProjectException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/projects";
    }

    @GetMapping("/gantt")
    public String test(Model model) {

        ArrayList<Task> tasks = new ArrayList<>();

        Task task1 = new Task(1L, "Rapport-skrivning", "Gruppe", null, null, "2021 11 10", "2021 12 17", 20);
        Task task2 = new Task(2L, "Domæne model", "Gruppe", null, null, "2021 11 25", "2021 11 26", 100);
        Task task3 = new Task(3L, "Virksomhed", "Gruppe", null, null, "2021 11 10", "2021 11 15", 20);


        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        Project project = new Project(1, "Eksamen", tasks, null);

        model.addAttribute("project", project);

        return "inspectProject";
    }

    @GetMapping("/project")
    public String showProject(@RequestParam (name="id") long id, Model model){
        model.addAttribute("project", PROJECT_SERVICE.getProjectById(id));
        return "inspectProject";
    }



}
