package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.TagServerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTagServerRepository extends JpaRepository<TagServerEntity, Long> {
}
