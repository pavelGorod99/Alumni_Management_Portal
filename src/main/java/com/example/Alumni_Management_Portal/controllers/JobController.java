package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.services.implementation.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/jobs")
public class JobController {

    private JobServiceImpl jobService;

    @Autowired
    public JobController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    private void create(@RequestBody JobDto jobDto) {
        jobService.create(jobDto);
    }
}
