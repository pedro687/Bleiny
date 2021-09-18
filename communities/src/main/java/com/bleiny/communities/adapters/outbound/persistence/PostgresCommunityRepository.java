package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.ports.CommunityRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PostgresCommunityRepository implements CommunityRepositoryPort {
    private final SpringDataCommunityRepository repository;
    private ModelMapper mapper;

    public PostgresCommunityRepository(SpringDataCommunityRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Community save(Community community) {
        var converterToModel = mapper.map(community, CommunityEntity.class);
        var save = repository.save(converterToModel);
        return  mapper.map(save, Community.class);
    }
}
