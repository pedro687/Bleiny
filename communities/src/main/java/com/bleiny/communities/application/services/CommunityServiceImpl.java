package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.CommunityServicePort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

@Slf4j
public class CommunityServiceImpl implements CommunityServicePort {

    private final CommunityRepositoryPort communityRepositoryPort;

    private final ModelMapper modelMapper;

    public CommunityServiceImpl(CommunityRepositoryPort communityRepositoryPort, ModelMapper mapper) {
        this.communityRepositoryPort = communityRepositoryPort;
        this.modelMapper = mapper;
    }

    @Override
    public CommunityDTO createCommunity(CommunityDTO dto) {
        log.info("Creating community: {}", dto);
        var convert = modelMapper.map(dto, Community.class);
        communityRepositoryPort.save(convert);
        return dto;
    }

    @Override
    public CommunityDTO findById(Long id) throws ApiException {
        var community = communityRepositoryPort.findById(id);
        return modelMapper.map(community, CommunityDTO.class);
    }

}
