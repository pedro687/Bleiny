package com.bleiny.communities.adapters.inbound.dtos;

import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TagServerDTO {

    private Tag tag;

    private Community community;
}
