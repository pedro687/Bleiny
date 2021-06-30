package com.spiet.bleiny.api.users.controllers;

import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.api.users.resources.UserResource;
import com.spiet.bleiny.api.users.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController implements UserResource {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO) {
        try {
            return ResponseEntity.status(201).body(userService.create(userDTO));
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
