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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @GetMapping("/project")
    public String showProject(@RequestParam (name="id") long id, Model model){
        Project project = PROJECT_SERVICE.getProjectById(id);

        ArrayList<Task> tasks = new ArrayList<>();

        //DateFormat df = new SimpleDateFormat("y M d");
        //Date date = new Date();
        //String dateStr = df.format(date);

        Task task1 = new Task(1L, "Rapport-skrivning", "Gruppe","2021 11 10", "2021 12 17", 20);
        Task task2 = new Task(2L, "Domæne model", "Gruppe", "2021 11 25", "2021 11 26", 100);
        Task task3 = new Task(3L, "Virksomhed", "Gruppe", "2021 11 10", "2021 11 15", 20);


        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);


        project.setTasks(tasks);

        System.out.println(project.getTasks().size());
        System.out.println(project.getProjectId());;

        model.addAttribute("project", project);
        return "inspectProject";
    }



}
