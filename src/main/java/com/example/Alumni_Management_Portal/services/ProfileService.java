package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAll();
    Profile getById(int id);
    Profile create(Profile profile);
    Profile update(Profile profile);
    void delete(int id);
}
