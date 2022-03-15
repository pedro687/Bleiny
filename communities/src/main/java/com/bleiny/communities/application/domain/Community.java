package com.bleiny.communities.application.domain;

import lombok.*;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Community {

    private String communityName;

    private Long communityLeaderId;

    private String description;

    private Long id;

}
