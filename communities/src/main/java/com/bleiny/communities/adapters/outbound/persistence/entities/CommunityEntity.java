package com.bleiny.communities.adapters.outbound.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
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

    @ManyToOne
    private UserEntity communityLeader;

    private String description;

    @OneToMany(mappedBy = "community")
    private List<RoomEntity> rooms;

    @Column(name = "member_quantity")
    private Integer memberQuantity;

    /*@ManyToMany
    @JoinTable(name = "TB_SERVER_MEMBER",
    joinColumns = @JoinColumn( name = "community_id"),
    inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<UserEntity> members;*/

    @ManyToMany
    @JoinTable(name = "TB_SERVER_LEADER",
            joinColumns = @JoinColumn( name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "leader_id"))
    private Set<UserEntity> leader;
}
