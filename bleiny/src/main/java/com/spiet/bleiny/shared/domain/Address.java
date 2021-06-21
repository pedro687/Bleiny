package com.spiet.bleiny.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uf;

    @Column
    private String city;

    @Column
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
