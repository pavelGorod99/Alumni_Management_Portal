package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.dto.LoginRequestDto;
import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.*;
import com.example.Alumni_Management_Portal.repositories.RoleRepository;
import com.example.Alumni_Management_Portal.repositories.UserRepository;
import com.example.Alumni_Management_Portal.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public String authenticateUser(LoginRequestDto loginRequestDto) throws ResourceNotFoundException{
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        Optional<User> opUser= userRepository.findByEmail(loginRequestDto.getEmail());
        if(opUser.isPresent())
        {
            User dbUser= opUser.get();
            if(bcrypt.matches(loginRequestDto.getPassword(), dbUser.getPassword())){
                return "Authenticated User";
            } else{
                return "Incorrect Password";
            }
        }
        throw new ResourceNotFoundException("User is not found");
    }


    @Override
    public String create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("A user is already registered with this email address: " + user.getEmail());
        }
        String roleName = userDto.getRole().getRole();
        Role role = roleRepository.findByRoleIgnoreCase(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setRole(roleName);
                    return roleRepository.save(newRole);
                });

        user.setRole(role);
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        String encryptedPwd= bcrypt.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        System.out.println("User email: " + user.getEmail());
        System.out.println("User id: " + user.getId());
        User savedUser = userRepository.save(user);
        return savedUser.getFirstName() + " added to database successfully";
    }

    @Override
    public void update(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userDto.getId()));
        modelMapper.map(userDto, existingUser);
        userRepository.save(existingUser);
    }


    @Override
    public void addJobExperience(Integer id, String jobExperience) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
        if (user.getRole().getRole().equalsIgnoreCase("Student")) {
            List<String> jobExperiences = user.getJobExperiences();
            jobExperiences.add(jobExperience);
            user.setJobExperiences(jobExperiences);

            userRepository.save(user);
        }else{
            System.out.println("Role of the user: " + user.getRole().getRole());
            throw new RuntimeException("Only students can add job experiences");
        }

    }


    @Override
    public void delete(int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with id " + id));

        user.setIsDeleted(true);
        userRepository.save(user);
    }

}
