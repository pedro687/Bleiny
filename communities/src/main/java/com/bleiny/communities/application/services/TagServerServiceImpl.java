package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.TagServerRepositoryPort;
import com.bleiny.communities.application.ports.TagServerServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    public TagServerDTO addTag(TagServerDTO tagServerDTO) {
        TagServer tagServer = TagServer.builder()
                .tag(tagServerDTO.getTag())
                .community(tagServerDTO.getCommunity())
                .build();

        return repository.create(tagServer);
    }

    @Override
    public List<ResponseTagServerDTO> filterByParameters(Long tagId, Pageable pageable, String namePage, String sort) {
        if (tagId != null && namePage == null) {
            return repository.findCommunitiesByTag(tagId, pageable);
        }

        if (tagId == null && namePage != null) {
            return repository.findCommunitiesByName(namePage, pageable);
        }

        if (tagId != null && namePage != null) {
            return repository.findCommunitiesByNameAndTag(namePage, tagId, pageable);
        }

        return communityServicePort
                .findCommunities(pageable);
    }


}
