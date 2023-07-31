package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(int id);

}
