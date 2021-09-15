package com.bleiny.communities.adapters.outbound.persistence.entities;

import com.bleiny.communities.application.domain.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class UserEntity implements Serializable {

    private String uuid;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;


}
