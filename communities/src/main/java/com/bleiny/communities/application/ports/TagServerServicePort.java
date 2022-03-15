package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;

public interface TagServerServicePort {
    void addTag(TagServerDTO tagServerDTO);
}
