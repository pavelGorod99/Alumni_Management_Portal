package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.EmailAlreadyExistsException;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.entities.User;
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
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
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
        return modelMapper.map(user, UserDto.class);
    }

    public String authenticateUser(UserDto userDto) throws ResourceNotFoundException{
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        Optional<User> opUser= userRepository.findById(userDto.getId());
        if(opUser.isPresent())
        {
            User dbUser= opUser.get();
            if(bcrypt.matches(userDto.getPassword(), dbUser.getPassword())){
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
        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyExistsException("A user is already registered with this email address: " + user.getEmail());
        }
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        String encryptedPwd= bcrypt.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        User savedUser = userRepository.save(user);
        return savedUser.getFirstName() + "added to database successfully";
    }

    @Override
    public void update(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userDto.getId()));
        modelMapper.map(userDto, existingUser);
        userRepository.save(existingUser);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
