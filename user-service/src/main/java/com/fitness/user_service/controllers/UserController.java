package com.fitness.user_service.controllers;


import com.fitness.user_service.dtos.UserRegisterRequest;
import com.fitness.user_service.dtos.UserResponse;
import com.fitness.user_service.models.User;
import com.fitness.user_service.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest registerRequest) {
        UserResponse userResponse=userService.register(registerRequest);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id){
        UserResponse userResponse=userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }
}
