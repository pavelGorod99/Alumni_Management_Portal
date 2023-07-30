package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//@Entity
@Setter
@Getter
public class EventAttendance {

//    @Id
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany
    @JoinColumn(name = "attendance_id")
    private List<User> attendance;
}
