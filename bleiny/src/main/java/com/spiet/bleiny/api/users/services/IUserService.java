package com.spiet.bleiny.api.users.services;

import com.spiet.bleiny.api.users.dto.ResponseUserDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.shared.infra.ApiException;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    UserDTO create(UserDTO userDTO) throws ApiException;

    UserDTO findByEmail(String email);
}
