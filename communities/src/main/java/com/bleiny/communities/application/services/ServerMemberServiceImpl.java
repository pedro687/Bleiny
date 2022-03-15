package com.bleiny.communities.application.services;

import com.bleiny.communities.adapters.inbound.dtos.EnjoyDataDTO;
import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import com.bleiny.communities.adapters.outbound.persistence.entities.ServerMemberEntity;
import com.bleiny.communities.adapters.outbound.persistence.entities.UserEntity;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.exceptions.ApiException;
import com.bleiny.communities.application.ports.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.List;

@Slf4j
public class ServerMemberServiceImpl implements ServerMemberServicePort {

    private final ServerMemberRepositoryPort serverMemberRepositoryPort;
    private final CommunityServicePort communityServicePort;
    private final UserServicePort userServicePort;
    private final ModelMapper modelMapper;

    public ServerMemberServiceImpl(ServerMemberRepositoryPort serverMemberRepositoryPort, CommunityServicePort communityServicePort, UserServicePort userServicePort, ModelMapper modelMapper) {
        this.serverMemberRepositoryPort = serverMemberRepositoryPort;
        this.communityServicePort = communityServicePort;
        this.userServicePort = userServicePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public void enjoyCommunity(ServerMemberEnjoyDTO dto) throws ApiException {
        try {
            log.info("Joining {} to {}", dto.getIdUser(), dto.getIdCommunity());

            if (memberAlreadyInCommunity(dto.getIdUser(), dto.getIdCommunity())) {
               throw  ApiException.conflict("Error on enjoying community" , "Usuário ja está nesta comunidade");
            }

            var user = userServicePort.findById(dto.getIdUser());
            var community = communityServicePort.findById(dto.getIdCommunity());

            var serverMember = ServerMemberEntity.builder()
                    .community(modelMapper.map(community, CommunityEntity.class))
                    .user(modelMapper.map(user, UserEntity.class)).build();

            serverMemberRepositoryPort.save(serverMember);
        } catch (Exception e) {
            log.error("Error on enjoying community: {}", e.getMessage());
            throw ApiException.conflict("Error on enjoying community" , e.getMessage());
        }
    }

    @Override
    public boolean memberAlreadyInCommunity(Long idUser, Long idCommunity) {
        return serverMemberRepositoryPort.memberAlreadyInServer(idUser, idCommunity);
    }
}
