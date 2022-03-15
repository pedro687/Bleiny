package com.spiet.bleiny.shared.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendUserToCommunityMessageDTO {
    private Long id;
    private String uuid;
    private String userName;
}
