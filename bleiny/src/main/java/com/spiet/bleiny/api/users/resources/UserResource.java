package com.spiet.bleiny.api.users.resources;

import com.spiet.bleiny.api.users.dto.ResponseUserDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/user")
public interface UserResource {
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> create(UserDTO userDTO);

    @GetMapping(path = "/findByEmail/{email}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> findByEmail(@PathVariable String email);

    @GetMapping
    String hello();
}
