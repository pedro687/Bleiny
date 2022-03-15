package com.bleiny.communities.adapters.inbound.consumer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveUserMessageDTO {
    private Long id;
    private String uuid;
    private String userName;
}
