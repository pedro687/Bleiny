package com.bleiny.communities.adapters.inbound.resources;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.application.domain.Community;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/v1/community")
@Api(value = "/v1/community", tags = "Community")
public interface CommunityResource {

    @PostMapping
    @ApiOperation(value = "Create an community", response = Community.class)
    public ResponseEntity<Community> createCommunity(CommunityDTO communityDTO);
}
