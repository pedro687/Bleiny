package com.bleiny.communities.adapters.inbound.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class CommunityDTO {

    @JsonProperty("community_name")
    @NotBlank
    private String communityName;

    @JsonProperty("leader_community_id")
    @NotBlank
    private String leader_community_id;


}
