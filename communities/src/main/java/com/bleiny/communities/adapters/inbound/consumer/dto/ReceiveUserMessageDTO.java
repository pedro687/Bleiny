package com.bleiny.communities.adapters.inbound.consumer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiveUserMessageDTO {
    private Long id;
    private String uuid;
    private String userName;
}
