package com.springsecuriry.controller;

import com.springsecuriry.entity.User;
import com.springsecuriry.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;


@RestController
public class CustomerController {

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String createUser(@RequestBody User user) {
       return userService.createUser(user);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello, Join Uall");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.getAllUser().stream().filter(user -> user.getId().equals(id)).findFirst();

        return userOptional
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
