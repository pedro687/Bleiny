package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.TagServerEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataTagServerRepository extends JpaRepository<TagServerEntity, Long> {
    List<TagServerEntity> findAllByTagIdOrderByCommunityMemberQuantityDesc(Long id);

    List<TagServerEntity> findAllByCommunityCommunityNameContainingIgnoreCaseOrderByCommunityMemberQuantityDesc(String name, Pageable pageable);

    List<TagServerEntity> findAllByCommunityCommunityNameContainingIgnoreCaseAndTagIdOrderByCommunityMemberQuantityDesc(String name, Long tagId, Pageable pageable);

}
