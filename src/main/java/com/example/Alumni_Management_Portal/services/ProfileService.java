package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAllProfiles();
    Profile getProfileById(int id);
    Profile createProfile(Profile profile);
    Profile updateProfile(Profile profile);
    void deleteProfile(int id);
}
