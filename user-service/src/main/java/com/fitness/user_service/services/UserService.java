package com.fitness.user_service.services;

import com.fitness.user_service.dtos.UserRegisterRequest;
import com.fitness.user_service.dtos.UserResponse;
import com.fitness.user_service.models.User;
import com.fitness.user_service.repositories.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(UserRegisterRequest registerRequest) {
        if(userRepository.existsByEmail(registerRequest.email())){
            throw new RuntimeException("Email already exists");
        }

        User user = dtoToDao(registerRequest, new User());
        User savedUser = userRepository.save(user);
        return daoToDto(savedUser);

    }

    private UserResponse daoToDto(User savedUser) {
        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getCreatedAt(),
                savedUser.getUpdatedAt()
        );
    }

    private User dtoToDao(UserRegisterRequest registerRequest, User user) {
        user.setEmail(registerRequest.email());
        user.setPassword(registerRequest.password());
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());
        return user;
    }

    public UserResponse getUserById(String id) {
        User user=userRepository.findById(id).orElseThrow(()->new RuntimeException("User not Found."));
        return daoToDto(user);
    }
}
