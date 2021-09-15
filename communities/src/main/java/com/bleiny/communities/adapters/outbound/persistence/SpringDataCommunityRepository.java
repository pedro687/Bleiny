package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataCommunityRepository extends JpaRepository<CommunityEntity, Long> {
}
