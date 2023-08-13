package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Entity
public class Insight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private Boolean surveyStatus;
    private LocalDate openDate;
    private LocalDate closeDate;

    @ManyToOne
    private User insightCreator;



//    @OneToMany
//    private List<SurveyQuestion> surveyQuestionList;

}
