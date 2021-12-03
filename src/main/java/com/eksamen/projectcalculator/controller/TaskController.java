package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.model.Task;
import com.eksamen.projectcalculator.domain.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        long projectId = id;
        String taskName = request.getParameter("name");
        String resource = request.getParameter("resource");
        String startDate = request.getParameter("start");
        String finishDate = request.getParameter("finish");




        int completion = Integer.parseInt(request.getParameter("completion"));

        TASK_SERVICE.createTask(projectId, taskName, resource, startDate, finishDate, completion);

        redirectAttributes.addAttribute("id", id);

        return "redirect:/project";
    }
}
