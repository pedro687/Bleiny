package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.outbound.persistence.SpringDataCommunityRepository;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.ServerMemberServicePort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CommunityServiceImpl implements CommunityServicePort {

    private final CommunityRepositoryPort communityRepositoryPort;
    private final ServerMemberServicePort serverMemberServicePort;
    private final ModelMapper modelMapper;
    private final SpringDataCommunityRepository springDataCommunityRepository;

    public CommunityServiceImpl(CommunityRepositoryPort communityRepositoryPort, ModelMapper mapper, ServerMemberServicePort serverMemberServicePort, SpringDataCommunityRepository springDataCommunityRepository) {
        this.communityRepositoryPort = communityRepositoryPort;
        this.modelMapper = mapper;
        this.serverMemberServicePort = serverMemberServicePort;
        this.springDataCommunityRepository = springDataCommunityRepository;
    }

    @Override
    public CommunityDTO createCommunity(CommunityDTO dto) throws ApiException {
        log.info("Creating community: {}", dto);
        var convert = modelMapper.map(dto, Community.class);
        communityRepositoryPort.save(convert);
        return dto;
    }

    @Override
    public CommunityDTO findById(Long id) throws ApiException {
        var community = communityRepositoryPort.findById(id);
        return converter(community);
    }

    @Override
    public Community findByUuid(String uuid) {
        return null;
    }

    @Override
    public CommunityDTO updateCommunity(CommunityDTO dto) throws ApiException {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        var repo = springDataCommunityRepository.findById(dto.getId()).get();
        repo.setMemberQuantity(dto.getMemberQuantity());
        springDataCommunityRepository.save(repo);
        return dto;
    }

    @Override
    public List<ResponseTagServerDTO> findCommunities(Pageable pageable) {
        return communityRepositoryPort.findAll(pageable).stream()
                .map(ts -> modelMapper.map(ts, ResponseTagServerDTO.class))
                .collect(Collectors.toList());
    }

    public CommunityDTO converter(Community community) {
        CommunityDTO communityDTO = new CommunityDTO();
        communityDTO.setCommunityName(community.getCommunityName());
        communityDTO.setCommunityLeaderId(community.getCommunityLeaderId());
        communityDTO.setDescription(community.getDescription());
        communityDTO.setMemberQuantity(community.getMemberQuantity());
        communityDTO.setId(community.getId());

        return communityDTO;
    }
}
