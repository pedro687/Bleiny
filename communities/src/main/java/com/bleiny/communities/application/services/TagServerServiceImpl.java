package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.adapters.outbound.persistence.PostgresTagServerRepository;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.TagServerRepositoryPort;
import com.bleiny.communities.application.ports.TagServerServicePort;
import org.modelmapper.ModelMapper;

public class TagServerServiceImpl implements TagServerServicePort {

    private final ModelMapper modelMapper;
    private final CommunityServicePort communityServicePort;
    private final TagServerRepositoryPort repository;

    public TagServerServiceImpl(ModelMapper modelMapper, CommunityServicePort communityServicePort, TagServerRepositoryPort repository) {
        this.modelMapper = modelMapper;
        this.communityServicePort = communityServicePort;
        this.repository = repository;
    }

    @Override
    public void addTag(TagServerDTO tagServerDTO) {
        TagServer tagServer = TagServer.builder()
                .tag(tagServerDTO.getTag())
                .community(tagServerDTO.getCommunity())
                .build();

        repository.create(tagServer);
    }
}
