package com.bleiny.communities.adapters.outbound.persistence;

import com.bleiny.communities.adapters.outbound.persistence.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTagRepository extends JpaRepository<TagEntity, Long> {
}
