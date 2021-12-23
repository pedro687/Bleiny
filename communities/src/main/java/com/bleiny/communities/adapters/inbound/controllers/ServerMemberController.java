package com.bleiny.communities.adapters.inbound.controllers;

import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.adapters.inbound.resources.ServerMemberResource;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.ServerMemberServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerMemberController implements ServerMemberResource {

    private final ServerMemberServicePort serverMemberServicePort;

    public ServerMemberController(ServerMemberServicePort serverMemberServicePort) {
        this.serverMemberServicePort = serverMemberServicePort;
    }

    @Override
    public ResponseEntity<?> enjoyCommunity(ServerMemberEnjoyDTO serverMemberEnjoyDTO) throws ApiException {
        serverMemberServicePort.enjoyCommunity(serverMemberEnjoyDTO);
        return ResponseEntity.status(204).build();
    }
}
