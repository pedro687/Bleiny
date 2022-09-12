package com.bleiny.communities.adapters.inbound.dtos;

import com.bleiny.communities.application.domain.Community;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTagServerDTO {
    Community community;
}
