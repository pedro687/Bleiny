package com.bleiny.communities.adapters.inbound.resources;


import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/v1/server")
@Api(value = "/v1/server", tags = "Server")
public interface ServerMemberResource {

    @PostMapping
    @ApiOperation(value = "Enjoying community")
    public ResponseEntity<?> enjoyCommunity(@RequestBody ServerMemberEnjoyDTO serverMemberEnjoyDTO) throws ApiException;
}
