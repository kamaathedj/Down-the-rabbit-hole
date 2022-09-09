package com.example.DeepHole.controllers;

import com.example.DeepHole.models.User;
import com.example.DeepHole.service.UserService;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class userController {

    private final UserService service;

    public userController(UserService service) {
        this.service = service;
    }


    @GetMapping("/")
    public List<User> getUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/wat")
    public String method(@CurrentSecurityContext SecurityContext context) {
        return context.getAuthentication().getName();
    }
}
