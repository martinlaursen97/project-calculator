package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.service.SubtaskService;
import com.eksamen.projectcalculator.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SubtaskController {
    private final TaskService TASK_SERVICE = new TaskService();
    private final SubtaskService SUBTASK_SERVICE = new SubtaskService();

    @GetMapping("/project/task/add-subtask")
    public String addSubtask(@RequestParam(name = "id") long taskId, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (TASK_SERVICE.taskIsUsers(userId, taskId)) {
            model.addAttribute("taskId", taskId);
            return "addSubtask";
        } else {
            return "error";
        }
    }

    @PostMapping("/project/task/add-subtask-verify")
    public String addSubtaskVerify(@RequestParam(name = "id") long taskId, WebRequest request, RedirectAttributes redirectAttributes) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (TASK_SERVICE.taskIsUsers(userId, taskId)) {
            String taskName = request.getParameter("name");
            String resource = request.getParameter("resource");
            String startDate = request.getParameter("start");
            String finishDate = request.getParameter("finish");
            int completion = Integer.parseInt(request.getParameter("completion"));
            double dailyWorkHours = Double.parseDouble(request.getParameter("hours"));
            double pricePerHour = Double.parseDouble(request.getParameter("priceprhr"));

            SUBTASK_SERVICE.createSubtask(taskId, taskName, resource, startDate, finishDate, completion, dailyWorkHours, pricePerHour);
            redirectAttributes.addAttribute("id", taskId);
            return "redirect:/project/task";
        } else {
            return "error";
        }

    }
}
