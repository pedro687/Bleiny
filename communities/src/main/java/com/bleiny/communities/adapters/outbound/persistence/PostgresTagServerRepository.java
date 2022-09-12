package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.adapters.outbound.persistence.entities.TagServerEntity;
import com.bleiny.communities.application.domain.Tag;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.ServerMemberServicePort;
import com.bleiny.communities.application.ports.TagServerRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    public TagServerDTO create(TagServer tagServer) {
        var res = repository.save(modelMapper.map(tagServer, TagServerEntity.class));
        return modelMapper.map(res, TagServerDTO.class);
    }

    @Override
    public List<ResponseTagServerDTO> findCommunitiesByTag(Long id, Pageable pageable) {
        var tagServer = repository.findAllByTagIdOrderByCommunityMemberQuantityDesc(id);
        return convertCommunitiesSearch(tagServer);
    }

    @Override
    public List<ResponseTagServerDTO> findCommunitiesByName(String name, Pageable pageable) {
       var communitiesList = repository.findAllByCommunityCommunityNameContainingIgnoreCaseOrderByCommunityMemberQuantityDesc(name, pageable);
        return convertCommunitiesSearch(communitiesList);
    }

    @Override
    public TagServerDTO findTagById(Long id) throws ApiException {
        return
                modelMapper.map(repository.findById(id).orElseThrow(() -> ApiException.notFound("Tag Not found", "Tag Not found")),
                        TagServerDTO.class);
    }

    @Override
    public List<ResponseTagServerDTO> findCommunitiesByNameAndTag(String namePage, Long tagId, Pageable pageable) {
        var communitiesList = repository.findAllByCommunityCommunityNameContainingIgnoreCaseAndTagIdOrderByCommunityMemberQuantityDesc(namePage, tagId, pageable);
        return convertCommunitiesSearch(communitiesList);
    }


    public List<ResponseTagServerDTO> convertCommunitiesSearch(List<TagServerEntity> tagServerEntityList) {
        return tagServerEntityList.stream()
                .map(ts -> modelMapper.map(ts, ResponseTagServerDTO.class))
                .collect(Collectors.toList());
    }
}
