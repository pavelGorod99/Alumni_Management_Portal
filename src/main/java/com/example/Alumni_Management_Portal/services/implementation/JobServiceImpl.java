package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.Job;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.JobRepository;
import com.example.Alumni_Management_Portal.services.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;
    private UserServiceImpl userService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        jobRepository.save(job);
    }

    @Override
    public List<JobDto> getAll() {
        List<Job> jobList = jobRepository.findAll();
        List<JobDto> jobDtoList = new ArrayList<>();
        jobList.forEach(job -> {
            JobDto jobDto = modelMapper.map(job, JobDto.class);
            jobDtoList.add(jobDto);
        });
        return jobDtoList;
    }

    @Override
    public void applyForJob(int userId, int jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            UserDto userDto = userService.getById(userId);
            User user = modelMapper.map(userDto, User.class);
            List<User> userList = job.getApplicants();
            userList.add(user);
            jobRepository.save(job);
        } else throw new RuntimeException("Job with id: " + jobId + " not found");
    }

    @Override
    public void closeTheJob(int id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setAvailable(false);
            jobRepository.save(job);
        }
    }
}
