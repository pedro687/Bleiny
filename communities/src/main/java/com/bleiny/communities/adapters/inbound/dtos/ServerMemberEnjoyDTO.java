package com.bleiny.communities.adapters.inbound.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerMemberEnjoyDTO {
    @JsonProperty(value = "id_user")
    private Long idUser;

    @JsonProperty(value = "id_community")
    private Long idCommunity;
}
