package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataCommunityRepository extends JpaRepository<CommunityEntity, Long> {
    CommunityEntity findByUuid(String uuid);

    List<CommunityEntity> findAllByOrderByMemberQuantityDesc();
}
