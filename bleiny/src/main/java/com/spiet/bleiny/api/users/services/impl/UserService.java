package com.spiet.bleiny.api.users.services.impl;

import com.spiet.bleiny.api.users.dto.ResponseUserDTO;
import com.spiet.bleiny.api.users.dto.UserDTO;
import com.spiet.bleiny.api.users.repositories.UserRepository;
import com.spiet.bleiny.api.users.services.IUserService;
import com.spiet.bleiny.api.users.services.IAddressService;
import com.spiet.bleiny.api.users.utils.UserConverter;
import com.spiet.bleiny.shared.exceptions.EmailAlreadyExistsException;
import com.spiet.bleiny.shared.exceptions.UserNotFoundException;
import com.spiet.bleiny.shared.infra.utils.EmailValidator;
import com.spiet.bleiny.shared.infra.utils.TellphoneValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserConverter userConverter;

    private final IAddressService addressService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserConverter userConverter, IAddressService addressService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
        this.addressService = addressService;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {

        try {
            new EmailValidator(userDTO.getEmail());
            new TellphoneValidator(userDTO.getTellphone());
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email Already Exists!");
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        var user = userConverter.toUser(userDTO);
        userRepository.save(user);

        return userDTO;
    }

    @Override
    public UserDTO findByEmail(String email) {
        var result = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        var converter = userConverter.toDto(result);
        return converter;
    }
}
