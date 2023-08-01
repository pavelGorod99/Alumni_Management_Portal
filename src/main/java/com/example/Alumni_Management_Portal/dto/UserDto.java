package com.example.Alumni_Management_Portal.dto;
import com.example.Alumni_Management_Portal.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
    private Boolean isActive;
    private Boolean isDeleted;
    private String username;
    private Role role;
}
