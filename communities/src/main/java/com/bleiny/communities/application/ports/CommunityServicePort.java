package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import org.springframework.data.domain.Pageable;

import javax.ws.rs.core.Response;
import java.util.List;

public interface CommunityServicePort {
    CommunityDTO createCommunity(CommunityDTO dto) throws ApiException;
    CommunityDTO findById(Long id) throws ApiException;
    Community findByUuid(String uuid);
    CommunityDTO updateCommunity(CommunityDTO dto) throws ApiException;

    List<ResponseTagServerDTO> findCommunities(Pageable pageable);

}
