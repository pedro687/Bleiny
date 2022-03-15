package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.TagServerDTO;
import com.bleiny.communities.adapters.inbound.resources.TagResource;
import com.bleiny.communities.application.ports.TagServerServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController implements TagResource {

    private final TagServerServicePort serverServicePort;

    public TagController(TagServerServicePort serverServicePort) {
        this.serverServicePort = serverServicePort;
    }

    @Override
    public ResponseEntity<Void> postTagCommunity(TagServerDTO tagServerDTO) {
        serverServicePort.addTag(tagServerDTO);
        return ResponseEntity.status(204).build();
    }
}
