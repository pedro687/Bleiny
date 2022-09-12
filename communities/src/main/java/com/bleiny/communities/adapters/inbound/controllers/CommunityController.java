package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.adapters.inbound.resources.CommunityResource;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.CommunityServicePort;
import com.bleiny.communities.application.ports.TagServerServicePort;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommunityController implements CommunityResource {

    private final CommunityServicePort communityServicePort;
    private final TagServerServicePort serverServicePort;

    public CommunityController(final CommunityServicePort communityServicePort, final TagServerServicePort serverServicePort) {
        this.communityServicePort = communityServicePort;
        this.serverServicePort = serverServicePort;
    }

    @Override
    public ResponseEntity<CommunityDTO> createCommunity(@RequestBody CommunityDTO communityDTO) throws ApiException {
        return ResponseEntity.ok(communityServicePort.createCommunity(communityDTO));
    }
    @Override
    public Page<ResponseTagServerDTO> findCommunitiesByFilter(Long tagId, int page, int size, String namePage, String sort) {
        Pageable pageable = PageRequest.of(page, size);
        var p = serverServicePort.filterByParameters(tagId, pageable, namePage, sort);
        return new PageImpl<>(p, pageable, p.size());
    }
}
