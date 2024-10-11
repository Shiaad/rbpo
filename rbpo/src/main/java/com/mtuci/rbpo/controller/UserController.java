package com.mtuci.rbpo.controller;

import org.springframework.web.bind.annotation.*;
import com.mtuci.rbpo.model.UserDB;
import com.mtuci.rbpo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDB> findAll() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody UserDB user) {
        userService.save(user);
    }
}