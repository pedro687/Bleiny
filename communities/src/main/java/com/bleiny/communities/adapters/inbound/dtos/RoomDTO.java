package com.bleiny.communities.adapters.inbound.dtos;

import com.bleiny.communities.application.domain.Community;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomDTO {
    @NotNull
    @NotBlank
    private String roomName;

    @NotNull
    private Boolean isVoice;

    @NotNull
    private Long communityId;
}
