package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.resources.CommunityResource;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.ports.CommunityServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityController implements CommunityResource {

    private CommunityServicePort communityServicePort;

    public CommunityController(CommunityServicePort communityServicePort) {
        this.communityServicePort = communityServicePort;
    }

    @Override
    public ResponseEntity<Community> createCommunity(CommunityDTO communityDTO) {
        return ResponseEntity.ok(communityServicePort.createCommunity(communityDTO));
    }
}
