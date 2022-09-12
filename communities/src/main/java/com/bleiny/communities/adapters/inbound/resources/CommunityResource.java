package com.bleiny.communities.adapters.inbound.resources;

import com.bleiny.communities.adapters.inbound.dtos.CommunityDTO;
import com.bleiny.communities.adapters.inbound.dtos.ResponseTagServerDTO;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/v1/community")
@Api(value = "/v1/community", tags = "Community")
public interface CommunityResource {

    @PostMapping
    @ApiOperation(value = "Create an community", response = Community.class)
    public ResponseEntity<CommunityDTO> createCommunity(@RequestBody @Valid CommunityDTO communityDTO) throws ApiException;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Response communities by filter tag", response = ResponseTagServerDTO.class)
    Page<ResponseTagServerDTO> findCommunitiesByFilter(
                                                    @RequestParam(required = false) Long tagId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(required = false) String namePage,
                                                    @RequestParam(defaultValue = "desc") String sort);
}


