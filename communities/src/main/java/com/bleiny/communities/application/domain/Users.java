package com.bleiny.communities.application.domain;


import lombok.Data;

import java.util.List;

@Data
public class Users {

    private String uuid;
    private Long id;
    private String username;
    private List<Roles> role;
}
