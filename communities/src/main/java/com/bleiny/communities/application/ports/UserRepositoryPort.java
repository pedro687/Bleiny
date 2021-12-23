package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;

import java.util.Optional;

public interface UserRepositoryPort {
     void save(Users user) throws ApiException;
     Optional<Users> findById(Long id) throws ApiException;
}
