package com.bleiny.communities.adapters.outbound.persistence.entities;

import lombok.*;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_TAG")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name")
    private String tagName;
}
