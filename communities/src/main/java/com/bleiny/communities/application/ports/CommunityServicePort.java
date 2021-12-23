package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;

public interface CommunityServicePort {
    CommunityDTO createCommunity(CommunityDTO dto);
    CommunityDTO findById(Long id) throws ApiException;
}
