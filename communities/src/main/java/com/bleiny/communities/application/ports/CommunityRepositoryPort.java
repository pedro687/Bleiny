package com.bleiny.communities.application.ports;

import com.bleiny.communities.application.domain.Community;

public interface CommunityRepositoryPort {
    Community save(Community community);
}
