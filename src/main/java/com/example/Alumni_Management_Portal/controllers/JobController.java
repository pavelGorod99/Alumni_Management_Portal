package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    private void create(@RequestBody JobDto jobDto) {
        jobService.create(jobDto);
    }

    @PutMapping(path = "/apply")
    public void applyForJob(@RequestParam int userId, @RequestParam int jobId) {
        jobService.applyForJob(userId, jobId);
    }

    @PutMapping(path = "/close-it/{id}")
    public void closeTheJob(@PathVariable int id) {
        jobService.closeTheJob(id);
    }
}
