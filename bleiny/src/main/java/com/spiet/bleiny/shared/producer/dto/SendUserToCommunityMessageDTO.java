package com.spiet.bleiny.shared.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendUserToCommunityMessageDTO {
    private Long id;
    private String uuid;
    private String userName;
}
