package com.bleiny.communities.adapters.outbound.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_TAG_SERVER")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TagServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CommunityEntity community;

    @ManyToOne
    private TagEntity tag;
}
