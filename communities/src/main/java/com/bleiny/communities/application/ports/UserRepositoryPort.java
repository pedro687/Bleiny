package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;

public interface UserRepositoryPort {
     void save(Users user) throws ApiException;
}
