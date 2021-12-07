package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.service.TaskService;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TaskController {

    private final TaskService TASK_SERVICE = new TaskService();

    @GetMapping("/project/add")
    public String addTask(@RequestParam(name="id") long id, Model model) {
        model.addAttribute("id", id);
        return "addTask";
    }

    @PostMapping("/project/addTaskVerify")
    public String addTaskVerify(@RequestParam(name="id") long id, WebRequest request, RedirectAttributes redirectAttributes) throws ParseException {
        String taskName = request.getParameter("name");
        String resource = request.getParameter("resource");
        String startDate = request.getParameter("start");
        String finishDate = request.getParameter("finish");
        int completion = Integer.parseInt(request.getParameter("completion"));
        TASK_SERVICE.createTask(id, taskName, resource, startDate, finishDate, completion);
        redirectAttributes.addAttribute("id", id);

        return "redirect:/project";
    }

    @GetMapping("project/task")
    public String inspectTask(@RequestParam long id, Model model) {
        System.out.println(id);
        return "inspectTask";
    }

    @GetMapping("/project/clear/confirm")
    public String clearConfirm(@RequestParam(name = "id") long id, RedirectAttributes redirectAttributes) {
        TASK_SERVICE.clearTasksByProjectId(id);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/project";
    }
}
