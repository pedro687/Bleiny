package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import com.bleiny.communities.adapters.outbound.persistence.entities.UserEntity;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import com.bleiny.communities.application.ports.UserServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PostgresCommunityRepository implements CommunityRepositoryPort {
    private final SpringDataCommunityRepository repository;
    private final UserServicePort userServicePort;

    private ModelMapper mapper;

    public PostgresCommunityRepository(SpringDataCommunityRepository repository, ModelMapper mapper,
                                       UserServicePort userServicePort) {
        this.repository = repository;
        this.mapper = mapper;
        this.userServicePort = userServicePort;
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
}
