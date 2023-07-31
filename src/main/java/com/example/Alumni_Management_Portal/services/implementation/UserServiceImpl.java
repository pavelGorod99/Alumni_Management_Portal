package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.entities.UserAlreadyExistsException;
import com.example.Alumni_Management_Portal.repositories.UserRepository;
import com.example.Alumni_Management_Portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.UserRepository;
import com.example.Alumni_Management_Portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User create(User user) {
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());
        User existingUserByUsername = userRepository.findByUsername(user.getUsername());

        if(existingUserByEmail != null) {
            throw new UserAlreadyExistsException("A user is already registered with this email address: " + user.getEmail());
        }

        if(existingUserByUsername != null) {
            throw new UserAlreadyExistsException("A user is already registered with this username: " + user.getUsername());
        }

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User existingUser = getById(user.getId());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        existingUser.setIsActive(user.getIsActive());
        existingUser.setIsDeleted(user.getIsDeleted());
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(int id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
