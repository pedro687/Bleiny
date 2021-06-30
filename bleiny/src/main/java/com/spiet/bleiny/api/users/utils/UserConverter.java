package com.spiet.bleiny.api.users.utils;

import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.shared.domain.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class UserConverter {

    public User toUser(UserDTO userDTO) {
        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setTellphone(userDTO.getTellphone());
        user.setUsername(userDTO.getUsername());
        return user;
    }

    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setTellphone(user.getTellphone());
        userDTO.setUsername(user.getUsername());

        return userDTO;
    }
}
