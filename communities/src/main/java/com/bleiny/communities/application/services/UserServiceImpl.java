package com.bleiny.communities.application.services;

import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.UserRepositoryPort;
import com.bleiny.communities.application.ports.UserServicePort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

@Slf4j
public class UserServiceImpl implements UserServicePort {

    private final UserRepositoryPort userRepositoryPort;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepositoryPort userRepositoryPort, ModelMapper modelMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createUser(Users dto) {
        try {
            userRepositoryPort.save(dto);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
