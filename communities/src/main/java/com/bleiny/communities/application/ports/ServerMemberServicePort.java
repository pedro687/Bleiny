package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.application.exceptions.ApiException;
import org.springframework.http.ResponseEntity;

public interface ServerMemberServicePort {
   void enjoyCommunity(ServerMemberEnjoyDTO dto) throws ApiException;
   boolean memberAlreadyInCommunity(Long idUser, Long idCommunity);
}
