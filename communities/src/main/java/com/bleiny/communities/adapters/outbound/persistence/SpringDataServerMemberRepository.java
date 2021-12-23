package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.ServerMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataServerMemberRepository extends JpaRepository<ServerMemberEntity, Long> {
}
