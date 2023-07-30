package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String company;
    private String title;
    private String description;
    private String location;
    private String type;

    @ManyToOne
    @JoinColumn(name = "job_creator_id")
    private User job_creator;

    @OneToMany
    private List<User> applicants;
}
