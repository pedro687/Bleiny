package com.spiet.bleiny.api.users.utils;

import com.spiet.bleiny.api.users.http.dto.UserDTO;
import com.spiet.bleiny.shared.domain.Address;
import com.spiet.bleiny.shared.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
