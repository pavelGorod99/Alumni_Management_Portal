package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.entities.Profile;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.repositories.ProfileRepository;
import com.example.Alumni_Management_Portal.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getById(int id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
    }

    @Override
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile update(Profile profile) {
        Profile existingProfile = getById(profile.getId());
        existingProfile.setUser(profile.getUser());
        existingProfile.setImagePath(profile.getImagePath());
        existingProfile.setGraduationYear(profile.getGraduationYear());
        existingProfile.setCourse(profile.getCourse());
        existingProfile.setIndustry(profile.getIndustry());
        existingProfile.setProfessionalAchievement(profile.getProfessionalAchievement());
        existingProfile.setEducationDetails(profile.getEducationDetails());
        return profileRepository.save(existingProfile);
    }

    @Override
    public void delete(int id) {
        Profile profile = getById(id);
        profileRepository.delete(profile);
    }
}
