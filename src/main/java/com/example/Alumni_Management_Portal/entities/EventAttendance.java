package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class EventAttendance {

    @Id
    @OneToOne
    private Event event;

    @OneToMany
    private List<User> attendance;
}
