package com.example.Alumni_Management_Portal.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter

public class InsightDto {
        private int id;

        private String title;
        private Boolean surveyStatus;
        private LocalDate openDate;
        private LocalDate closeDate;
}
