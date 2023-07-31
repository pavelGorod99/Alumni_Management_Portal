package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.entities.Job;
import com.example.Alumni_Management_Portal.repositories.JobRepository;
import com.example.Alumni_Management_Portal.services.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
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

    }
}
