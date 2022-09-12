package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagServerServicePort {
    TagServerDTO addTag(TagServerDTO tagServerDTO);

    List<ResponseTagServerDTO> filterByParameters(Long id, Pageable pageable, String namePage, String sort);

}
