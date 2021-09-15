package com.bleiny.communities.adapters.outbound.persistence.entities;

import com.bleiny.communities.application.domain.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_COMMUNITY")
public class CommunityEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid = UUID.randomUUID().toString();
    private String communityName;

    private Long communityLeader_id;

    @OneToMany(mappedBy = "id_community")
    private List<RoomEntity> rooms;

    @ManyToMany
    private List<UserEntity> members;

    @OneToMany
    private List<RoleEntity> roles;
}
