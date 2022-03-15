package com.bleiny.communities.adapters.inbound.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommunityDTO {

    @JsonAlias("community_name")
    @NotBlank
    private String communityName;

    @JsonAlias("leader_community_id")
    @NotNull
    private Long communityLeaderId;

    @NotBlank
    private String description;

}
