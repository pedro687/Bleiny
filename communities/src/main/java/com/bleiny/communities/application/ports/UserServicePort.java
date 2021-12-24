package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Users;
import com.bleiny.communities.application.exceptions.ApiException;

public interface UserServicePort {
    void createUser(Users dto) throws ApiException;
    Users findById(Long id) throws ApiException;
}
