package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean isActive;
    private Boolean isDeleted;

    @OneToOne
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
//    @Enumerated(EnumType.STRING)
//    private Almuni almuni;


    @ElementCollection
    private List<String> jobExperiences = new ArrayList<>();
}
