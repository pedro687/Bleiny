package com.bleiny.communities.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    private String roomName;
    private Boolean isVoice;
    private Long communityId;
}
