package com.bleiny.communities.adapters.inbound.utils;

import com.bleiny.communities.adapters.inbound.consumer.dto.ReceiveUserMessageDTO;
import com.bleiny.communities.application.domain.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public Users messageToUser(ReceiveUserMessageDTO dto) {
        var user = new Users();
        user.setUsername(dto.getUserName());
        user.setUuid(dto.getUuid());
        user.setId(dto.getId());

        return user;
    }
}
