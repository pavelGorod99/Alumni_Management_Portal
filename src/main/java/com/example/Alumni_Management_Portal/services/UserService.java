package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getById(int id);
    UserDto create(UserDto userDto);
    void update(UserDto userDto);
    void delete(int id);

}
