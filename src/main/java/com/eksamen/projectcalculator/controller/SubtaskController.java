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

    /*
    Step 1:  Brugeren er inde på en task side og trykker Add subtask.
    Dette endpoint giver brugeren en form at udfylde.
    */
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

    /*
    Step 2:  Brugeren har udfyldt formen, og trykker add.
    Inputs fra HTML formen hives ud med WebRequest, og sendes igennem lagene
    hvor de til sidst når Subtask repository, og opretter en ny subtask i databasen.
    */
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

    @GetMapping("/project/task/subtask")
    public String showSubtask(@RequestParam(name = "id") long subtaskId, Model model, WebRequest request) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (SUBTASK_SERVICE.subtaskIsUsers(userId, subtaskId)) {
            model.addAttribute("subtask", SUBTASK_SERVICE.getSubtaskById(subtaskId));
            model.addAttribute("task", TASK_SERVICE.getTaskBySubtaskId(subtaskId));

            return "inspectSubtask";
        } else {
            return "error";
        }
    }

    @GetMapping("/project/task/subtask/delete")
    public String deleteSubtask(@RequestParam(name = "id") long subtaskId, WebRequest request, Model model) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (SUBTASK_SERVICE.subtaskIsUsers(userId, subtaskId)) {
            model.addAttribute("subtaskId", subtaskId);
            model.addAttribute("message", "Are you sure? This will delete the subtask.");
            return "deleteSubtask";
        } else {
            return "error";
        }
    }

    @GetMapping("/project/task/subtask/delete-confirm")
    public String deleteSubtaskConfirm(@RequestParam(name = "id") long subtaskId, WebRequest request, RedirectAttributes redirectAttributes)  {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (SUBTASK_SERVICE.subtaskIsUsers(userId, subtaskId)) {
            long projectId = SUBTASK_SERVICE.getProjectIdBySubtaskId(subtaskId);
            redirectAttributes.addAttribute("id", projectId);
            SUBTASK_SERVICE.deleteSubtaskById(subtaskId);
            return "redirect:/project";
        } else {
            return "error";
        }
    }

    // Ændre færdigheds procenten
    @PostMapping("/project/task/subtask/config")
    public String configSubtask(@RequestParam(name = "id") long subtaskId, WebRequest request, RedirectAttributes redirectAttributes) {
        Long userId = (Long) request.getAttribute("userId", WebRequest.SCOPE_SESSION);
        if (userId == null) return "login";

        if (SUBTASK_SERVICE.subtaskIsUsers(userId, subtaskId)) {
            int percent = Integer.parseInt(request.getParameter("percent"));
            SUBTASK_SERVICE.updateSubtaskPercentById(subtaskId, percent);
            redirectAttributes.addAttribute("id", subtaskId);
            return "redirect:/project/task/subtask";
        } else {
            return "error";
        }
    }
}
