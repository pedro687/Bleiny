package com.spiet.bleiny.api.users.utils;

import com.spiet.bleiny.api.users.dto.AddressDTO;
import com.spiet.bleiny.api.users.dto.ResponseUserDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.shared.domain.User;
import com.spiet.bleiny.shared.producer.dto.SendUserToCommunityMessageDTO;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class UserConverter {

    @Autowired
    private AddressConverter addressConverter;

    public User toUser(UserDTO userDTO) {
        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setTellphone(userDTO.getTellphone());
        user.setUsername(userDTO.getUsername());
        var address = addressConverter.toAddress(userDTO.getAddress(), user);
        user.setAddress(address);


        return user;
    }

    public UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setTellphone(user.getTellphone());
        userDTO.setUsername(user.getUsername());
        var address = addressConverter.toDto(user.getAddress());
        userDTO.setAddress(address);
        return userDTO;
    }

    public ResponseUserDTO toResponseDto(User user) {
        var converter = new ResponseUserDTO();
        converter.setEmail(user.getEmail());
        return converter;
    }

    public SendUserToCommunityMessageDTO userToMessage(User user) {
        var message = new SendUserToCommunityMessageDTO();
        message.setUserName(user.getUsername());
        message.setUuid(user.getUuid());
        message.setId(user.getId());

        return message;
    }
}
