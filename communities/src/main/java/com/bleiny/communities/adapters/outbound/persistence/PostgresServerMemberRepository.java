package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.inbound.dtos.EnjoyDataDTO;
import com.bleiny.communities.adapters.inbound.dtos.ServerMemberEnjoyDTO;
import com.bleiny.communities.adapters.outbound.persistence.entities.ServerMemberEntity;
import com.bleiny.communities.application.domain.ServerMember;
import com.bleiny.communities.application.ports.ServerMemberRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Slf4j
public class PostgresServerMemberRepository implements ServerMemberRepositoryPort {
    private final SpringDataServerMemberRepository repository;
    private final ModelMapper modelMapper;

    public PostgresServerMemberRepository(SpringDataServerMemberRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(ServerMemberEntity serverMemberEntity) {
        repository.save(serverMemberEntity);
    }

    @Override
    public boolean memberAlreadyInServer(Long idUser, Long idCommunity) {
        return repository.existsByUserIdAndCommunityId(idUser, idCommunity);
    }
}
