package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import com.bleiny.communities.adapters.outbound.persistence.entities.UserEntity;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.ServerMemberRepositoryPort;
import com.bleiny.communities.application.ports.UserServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class PostgresCommunityRepository implements CommunityRepositoryPort {
    private final SpringDataCommunityRepository repository;
    private final UserServicePort userServicePort;
    private final ServerMemberRepositoryPort serverMemberRepositoryPort;

    private ModelMapper mapper;

    public PostgresCommunityRepository(SpringDataCommunityRepository repository, ModelMapper mapper,
                                       UserServicePort userServicePort, ServerMemberRepositoryPort serverMemberRepositoryPort) {
        this.repository = repository;
        this.mapper = mapper;
        this.userServicePort = userServicePort;
        this.serverMemberRepositoryPort = serverMemberRepositoryPort;
    }

    @Override
    public Community save(Community community) throws ApiException {
        var converterToModel = mapper.map(community, CommunityEntity.class);
        converterToModel.setId(null);
        var userEntity = userServicePort.findById(community.getCommunityLeaderId());
        converterToModel.setCommunityLeader(mapper.map(userEntity, UserEntity.class));

        var save = repository.save(converterToModel);
        return mapper.map(save, Community.class);
    }

    @Override
    public Community findById(Long id) throws ApiException {
        var user = repository.findById(id).orElseThrow(() -> ApiException.notFound("Community Not Found", "Comunidade não encontrado"));
        return mapper.map(user, Community.class);
    }

    @Override
    public Community findByUud(String uuid) throws ApiException {
        return mapper.map(repository.findByUuid(uuid), Community.class);
    }

    @Override
    public List<ResponseTagServerDTO> findAll(Pageable pageable) {
        var communities = repository.findAllByOrderByMemberQuantityDesc();
        return communities
                .stream()
                .map(this::converter)
                .collect(Collectors.toList());
    }

    private ResponseTagServerDTO converter(CommunityEntity community) {
        Community communityDomain = new Community();

        communityDomain.setCommunityLeaderId(community.getCommunityLeader().getId());
        communityDomain.setCommunityName(community.getCommunityName());
        communityDomain.setDescription(community.getDescription());
        communityDomain.setId(community.getId());
        communityDomain.setMemberQuantity(community.getMemberQuantity());
        ResponseTagServerDTO responseTagServerDTO = new ResponseTagServerDTO();
        responseTagServerDTO.setCommunity(communityDomain);
        return responseTagServerDTO;
    }
}
