package com.spiet.bleiny.api.users.services;

import com.spiet.bleiny.api.users.http.dto.UserDTO;

public interface IUserService {
    UserDTO create(UserDTO userDTO);
}
