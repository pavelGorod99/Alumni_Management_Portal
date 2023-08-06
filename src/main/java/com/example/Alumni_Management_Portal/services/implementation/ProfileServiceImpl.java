package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.ProfileDto;
import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.Profile;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.repositories.ProfileRepository;
import com.example.Alumni_Management_Portal.services.ProfileService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    private HttpSession httpSession;

    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProfileDto> getAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profile -> modelMapper.map(profile, ProfileDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProfileDto getById(int id) {
        Optional<Profile> profile= profileRepository.findById(id);
        if(profile.isPresent()){
            ProfileDto profileDto= modelMapper.map(profile, ProfileDto.class);
            return profileDto;
        } else throw new ResourceNotFoundException("Profile not found with id " + id);
    }

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        Profile savedProfile = profileRepository.save(profile);
        return modelMapper.map(savedProfile, ProfileDto.class);
    }


    @Override
    public void update(ProfileDto profileDto) {
        UserDto loggedInUser = (UserDto) httpSession.getAttribute("user");

        if (loggedInUser == null) { // If no user is logged in
            throw new ResourceNotFoundException("No user is logged in");
        }

        if (loggedInUser.getRole().getRole().equals("Student") || loggedInUser.getRole().getRole().equals("Faculty")) {
            if (!loggedInUser.getId().equals(profileDto.getUser().getId())) {
                throw new ResourceNotFoundException("You are not authorized to edit this profile");
            }

            Profile existingProfile = profileRepository.findById(profileDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + profileDto.getId()));
            modelMapper.map(profileDto, existingProfile);
            profileRepository.save(existingProfile);
        } else {
            throw new ResourceNotFoundException("Only Faculty or Students can edit profile information");
        }
    }


    @Override
    public void delete(int id) {

        profileRepository.deleteById(id);
    }
}
