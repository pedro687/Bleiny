package com.bleiny.communities.adapters.inbound.dtos;

import com.bleiny.communities.application.domain.Community;
import com.bleiny.communities.application.domain.Tag;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagServerDTO {

    @NotNull
    @JsonAlias(value = "tag")
    private Tag tag;

    @NotNull
    @JsonAlias(value = "community")
    private Community community;
}
