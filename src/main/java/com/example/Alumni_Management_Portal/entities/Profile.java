package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Entity
@Setter
@Getter
public class Profile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String imagePath;
    private int graduationYear;

    @OneToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    private String professionalAchievement;
    private String educationDetails;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;
}