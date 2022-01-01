package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.TagServerEntity;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.ports.TagServerRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PostgresTagServerRepository implements TagServerRepositoryPort {

    private final ModelMapper modelMapper;
    private final SpringDataTagServerRepository repository;

    public PostgresTagServerRepository(ModelMapper modelMapper, SpringDataTagServerRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public void create(TagServer tagServer) {
        repository.save(modelMapper.map(tagServer, TagServerEntity.class));
    }
}
