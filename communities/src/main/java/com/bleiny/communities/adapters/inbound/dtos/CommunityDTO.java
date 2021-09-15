package com.bleiny.communities.adapters.inbound.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityDTO {

    @JsonProperty("community_name")
    private String communityName;

    @JsonProperty("community_leader")
    private String community_leader;


}
