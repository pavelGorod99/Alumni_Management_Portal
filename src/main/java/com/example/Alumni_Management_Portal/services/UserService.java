package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    UserDto getById(int id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(int id);

}
