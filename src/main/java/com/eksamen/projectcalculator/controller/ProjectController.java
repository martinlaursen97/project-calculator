package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.model.Project;
import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ProjectController {

    private final ProjectService PROJECT_SERVICE = new ProjectService();

    @GetMapping("/add")
    public String createProject() {
        return "addProject";
    }

    @PostMapping("/addVerify")
    public String createProjectVerify(WebRequest webRequest) {
        String projectName = webRequest.getParameter("name");
        if (projectName.length() != 0) {
            PROJECT_SERVICE.createProject(projectName);
        }
        // Go to Exception page
        return "";
    }

    //@GetMapping("/")
    //public String test(){
//
    //    Task task = new Task(1,"Første task", "Denne task er vigtig",
    //            5,  8, 120);
//
//
    //    Project project1 = new Project("Første projekt", "Vigtigt projekt");
    //    project1.addTask(task);
    //    project1.getDeadline();
    //    return "index";
    //}


}
