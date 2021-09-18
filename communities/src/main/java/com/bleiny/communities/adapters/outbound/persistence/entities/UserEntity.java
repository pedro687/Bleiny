package com.bleiny.communities.adapters.outbound.persistence.entities;

import com.bleiny.communities.application.domain.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class UserEntity implements Serializable {

    @Id
    private Long id;

    private String uuid;

    private String username;

    @ManyToMany(mappedBy = "members")
    private Set<CommunityEntity> communities;


    @ManyToMany(mappedBy = "leader")
    private Set<CommunityEntity> community_leader;
}
