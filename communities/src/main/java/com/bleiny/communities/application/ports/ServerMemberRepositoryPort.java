package com.bleiny.communities.application.ports;

import com.bleiny.communities.adapters.inbound.dtos.EnjoyDataDTO;
import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.adapters.outbound.persistence.entities.ServerMemberEntity;
import com.bleiny.communities.application.domain.ServerMember;

public interface ServerMemberRepositoryPort {
    void save(ServerMemberEntity serverMemberEntity);
    boolean memberAlreadyInServer(Long idUser, Long idCommunity);

}
