package com.bleiny.communities.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Community {

    private String communityName;

    private Long communityLeaderId;

    private String description;

}
