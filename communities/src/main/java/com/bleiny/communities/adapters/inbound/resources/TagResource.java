package com.bleiny.communities.adapters.inbound.resources;

import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/v1/tag")
@Api(value = "Operations about tags", tags = "Tags")
public interface TagResource {

    @PostMapping
    @ApiOperation(value = "Add tag on community", response = Void.class)
    public ResponseEntity<Void> postTagCommunity(@RequestBody @Valid TagServerDTO tagServerDTO);
}
