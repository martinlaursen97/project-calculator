package com.eksamen.projectcalculator.controller;

import com.eksamen.projectcalculator.domain.model.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class TaskController {

    @GetMapping("addTask")
    public String addTask(){

        return "addTask";
    }

    @PostMapping("/addTaskVerify")
    public String addTaskVerify() {

        return "";
    }
}
