package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Tag;

public interface PostgresTagRepositoryPort {
    void create(Tag tag);
}
