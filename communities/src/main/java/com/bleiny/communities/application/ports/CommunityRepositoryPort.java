package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;

public interface CommunityRepositoryPort {
    Community save(Community community) throws ApiException;
    Community findById(Long id) throws ApiException;
    Community findByUud(String uuid) throws ApiException;
}
