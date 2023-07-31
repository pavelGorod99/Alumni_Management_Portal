package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    User create(User user);
    User update(User user);
    void delete(int id);

}
