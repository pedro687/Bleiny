package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.ServerMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataServerMemberRepository extends JpaRepository<ServerMemberEntity, Long> {
    boolean existsByUserIdAndCommunityId(Long idUser, Long idCommunity);

    Long countAllByCommunityId(Long id);
}
