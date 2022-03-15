package com.bleiny.communities.adapters.inbound.dtos;

import com.bleiny.communities.adapters.outbound.persistence.entities.UserEntity;
import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnjoyDataDTO {
    private Users user;
    private Community community;
}
