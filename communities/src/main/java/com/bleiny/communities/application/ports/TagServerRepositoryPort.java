package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.TagServer;
import com.bleiny.communities.application.exceptions.ApiException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagServerRepositoryPort {
    public TagServerDTO create(TagServer tagServer);

    List<ResponseTagServerDTO> findCommunitiesByTag(Long id, Pageable pageable);

    List<ResponseTagServerDTO> findCommunitiesByName(String name, Pageable pageable);

    public TagServerDTO findTagById(Long id) throws ApiException ;

    List<ResponseTagServerDTO> findCommunitiesByNameAndTag(String namePage, Long tagId, Pageable pageable);


}
