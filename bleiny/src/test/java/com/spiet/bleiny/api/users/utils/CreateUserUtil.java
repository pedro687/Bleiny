package com.spiet.bleiny.api.users.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.api.users.dto.AddressDTO;

@Component
public class CreateUserUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO createDto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(passwordEncoder.encode("12345"));
        userDTO.setUsername("Jon Doe");
        userDTO.setEmail("JonDoe@email.com");
        userDTO.setTellphone("13996403088");
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUf("SP");
        addressDTO.setCity("Itanhaém");
        addressDTO.setCountry("Brazil");
        userDTO.setAddress(addressDTO);

        return userDTO;
    }
}
