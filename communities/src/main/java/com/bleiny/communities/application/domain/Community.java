package com.bleiny.communities.application.domain;

import java.util.List;

public class Community {

    private String communityName;

    private Long leader_community_id;

    private List<Room> rooms;

    private List<Users> members;

    private List<Roles> roles;
}
