package com.spiet.bleiny.api.users.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddressDTO {

    private String uf;

    private String city;

    private String country;
}
