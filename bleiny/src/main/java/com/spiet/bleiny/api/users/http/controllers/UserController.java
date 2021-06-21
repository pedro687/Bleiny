package com.spiet.bleiny.api.users.http.controllers;

import com.spiet.bleiny.api.users.http.dto.UserDTO;
import com.spiet.bleiny.api.users.resources.UserResource;
import com.spiet.bleiny.api.users.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
            return ResponseEntity.ok(userService.create(userDTO));
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
