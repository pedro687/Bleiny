package com.bleiny.communities.adapters.outbound.persistence.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "TB_SERVER_MEMBER")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private CommunityEntity community;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private UserEntity user;
}
