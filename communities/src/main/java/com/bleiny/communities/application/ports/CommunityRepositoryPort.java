package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityRepositoryPort {
    Community save(Community community) throws ApiException;
    Community findById(Long id) throws ApiException;
    Community findByUud(String uuid) throws ApiException;

    List<ResponseTagServerDTO> findAll(Pageable pageable);
}
