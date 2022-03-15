package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.TagEntity;
import com.bleiny.communities.application.domain.Tag;
import com.bleiny.communities.application.ports.PostgresTagRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Slf4j
public class PostgresTagRepository implements PostgresTagRepositoryPort {

    SpringDataTagRepository springDataTagRepository;

    private ModelMapper mapper;

    public PostgresTagRepository(SpringDataTagRepository springDataTagRepository) {
        this.springDataTagRepository = springDataTagRepository;
    }

    @Override
    public void create(Tag tag) {
        var tagEntity = TagEntity.builder()
                .tagName(tag.getTagName())
                .id(tag.getId())
                .build();

        springDataTagRepository.save(tagEntity);
    }
}
