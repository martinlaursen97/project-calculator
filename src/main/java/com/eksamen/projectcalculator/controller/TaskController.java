package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.service.ProjectService;
import com.eksamen.projectcalculator.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.ParseException;

@Controller
public class TaskController {

    private final TaskService TASK_SERVICE = new TaskService();
    private final ProjectService PROJECT_SERVICE = new ProjectService();

    @GetMapping("/project/add")
    public String addTask(@RequestParam(name="id") long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            model.addAttribute("id", id);
            return "addTask";
        } else {
            return "error";
        }
    }

    @PostMapping("/project/addTaskVerify")
    public String addTaskVerify(@RequestParam(name="id") long id, WebRequest request, RedirectAttributes redirectAttributes) throws ParseException {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            String taskName = request.getParameter("name");
            String resource = request.getParameter("resource");
            String startDate = request.getParameter("start");
            String finishDate = request.getParameter("finish");
            int completion = Integer.parseInt(request.getParameter("completion"));
            double dailyWorkHours = Double.parseDouble(request.getParameter("hours"));
            double pricePerHour = Double.parseDouble(request.getParameter("priceprhr"));

            TASK_SERVICE.createTask(id, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
            redirectAttributes.addAttribute("id", id);

            return "redirect:/project";
        } else {
            return "error";
        }
    }


    // FIX DET HER... skal give project id OG task id - se i projects html
    @GetMapping("project/task")
    public String inspectTask(@RequestParam long projectId, @RequestParam long id, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            model.addAttribute("task", TASK_SERVICE.getTaskById(projectId));
            return "inspectTask";
        } else {
            return "error";
        }
    }

    @GetMapping("/project/clear/confirm")
    public String clearConfirm(@RequestParam(name = "id") long id, RedirectAttributes redirectAttributes, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (PROJECT_SERVICE.projectIsUsers(userId, id)) {
            TASK_SERVICE.clearTasksByProjectId(id);
            redirectAttributes.addAttribute("id", id);
            return "redirect:/project";
        } else {
            return "error";
        }
    }
}
