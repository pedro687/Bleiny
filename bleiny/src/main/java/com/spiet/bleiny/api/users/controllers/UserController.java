package com.spiet.bleiny.api.users.controllers;

import com.spiet.bleiny.api.users.dto.ResponseUserDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.api.users.resources.UserResource;
import com.spiet.bleiny.api.users.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
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

    @Override
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        log.info("Email recebido: {}", email);
        try {
            return ResponseEntity.ok().body(userService.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public String hello() {
        return "Hello";
    }

}
