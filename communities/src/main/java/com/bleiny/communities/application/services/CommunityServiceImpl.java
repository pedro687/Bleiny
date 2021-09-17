package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;
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
    public Community createCommunity(CommunityDTO dto) {
        log.info("Criando comunidade: {}", dto);
        var convert = modelMapper.map(dto, Community.class);
        return communityRepositoryPort.save(convert);
    }
}