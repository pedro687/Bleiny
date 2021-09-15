package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;

public interface CommunityServicePort {
    Community createCommunity(CommunityDTO dto);
}
